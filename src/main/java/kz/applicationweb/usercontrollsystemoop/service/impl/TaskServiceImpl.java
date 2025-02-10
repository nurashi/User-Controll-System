package kz.applicationweb.usercontrollsystemoop.service.impl;

import kz.applicationweb.usercontrollsystemoop.dto.request.TaskRequest;
import kz.applicationweb.usercontrollsystemoop.dto.response.TaskResponse;
import kz.applicationweb.usercontrollsystemoop.model.task.Task;
import kz.applicationweb.usercontrollsystemoop.repository.TaskRepository;
import kz.applicationweb.usercontrollsystemoop.repository.EmployeeRepository;
import kz.applicationweb.usercontrollsystemoop.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public TaskResponse getTaskById(Long id) {
        Task task = taskRepository.findByIdWithDetails(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        return convertToResponseDto(task);
    }

    @Override
    public List<TaskResponse> getAllTasks() {
        return taskRepository.findAllWithDetails().stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public TaskResponse createTask(TaskRequest requestDto) {
        Task task = new Task();
        updateTaskFromDto(task, requestDto);
        Task savedTask = taskRepository.save(task);
        return convertToResponseDto(savedTask);
    }

    @Override
    public TaskResponse updateTask(Long id, TaskRequest requestDto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        updateTaskFromDto(task, requestDto);
        Task updatedTask = taskRepository.save(task);
        return convertToResponseDto(updatedTask);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    private void updateTaskFromDto(Task task, TaskRequest dto) {
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setEmployeeId(dto.getEmployeeId());
        task.setStatusId(dto.getStatusId());
    }

    private TaskResponse convertToResponseDto(Task task) {
        TaskResponse dto = new TaskResponse();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setEmployeeId(task.getEmployeeId());
        dto.setStatusId(task.getStatusId());
        dto.setCreatedAt(task.getCreatedAt());
        dto.setUpdatedAt(task.getUpdatedAt());
        
        // Set status name if available
        if (task.getStatus() != null) {
            dto.setStatusName(task.getStatus().getName());
        }
        
        // Get employee full name
        employeeRepository.findById(task.getEmployeeId())
                .ifPresent(employee -> 
                    dto.setEmployeeFullName(employee.getName() + " " + employee.getSurname())
                );
        
        return dto;
    }

    @Override
    public List<TaskResponse> getTasksByEmployeeId(Long employeeId) {
        return taskRepository.findAllByEmployeeIdWithDetails(employeeId).stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }
}