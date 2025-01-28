//package kz.applicationweb.usercontrollsystemoop.statistics;
//
//
//import kz.applicationweb.usercontrollsystemoop.model.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import kz.applicationweb.usercontrollsystemoop.model.UserService;
//
//import java.util.Map;
//
//@RestController
//@RequestMapping(path = "/statistics")
//public class userStatistics {
//
//    private final UserService userService;
//    private User user;
//
//    @Autowired
//    public userStatistics(UserService userService){
//        this.userService = userService;
//    }
//
//
//    @GetMapping("/age")
//    public double ageStats(){
//        double avarageAge = 0;
//        for(User user : userService.getUsers()){
//            avarageAge += user.calculateAge();
//        }
//        return avarageAge/userService.getUsers().size();
//    }
//
////    public String mostCommonJob(){
////        Map<String, Integer> jobCount = user.jobCount();
////        String mostCommonJob = "";
////        int max = 0;
////        for(Map.Entry<String, Integer> entry : jobCount.entrySet()){
////            if(entry.getValue() > max){
////                max = entry.getValue();
////                mostCommonJob = entry.getKey();
////            }
////        }
////        return mostCommonJob;
////    }
//}
