package kz.applicationweb.usercontrollsystemoop.model.statistics;

import jakarta.persistence.*;
import kz.applicationweb.usercontrollsystemoop.model.User;
@Entity
@Table(name = "OOP_User_Jobs")
public class UserJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 255, nullable = false, name = "job_title")
    private String jobTitle;

    @Column(length = 255, nullable = false, name = "company_name")
    private String companyName;

    @Column(nullable = false, name = "start_date")
    private java.time.LocalDate startDate;

    @Column(name = "end_date")
    private java.time.LocalDate endDate;

    @Column(length = 1000, name = "job_description")
    private String jobDescription;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public UserJob() {}

    public UserJob(String jobTitle,
                   String companyName,
                   java.time.LocalDate startDate,
                   java.time.LocalDate endDate,
                   String jobDescription,
                   User user) {
        this.jobTitle = jobTitle;
        this.companyName = companyName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.jobDescription = jobDescription;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public java.time.LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(java.time.LocalDate startDate) {
        this.startDate = startDate;
    }

    public java.time.LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(java.time.LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserJob{" +
                "id=" + id +
                ", jobTitle='" + jobTitle + '\'' +
                ", companyName='" + companyName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", jobDescription='" + jobDescription + '\'' +
                ", user=" + user.getId() +
                '}';
    }
}
