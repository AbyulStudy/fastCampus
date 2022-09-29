package org.example;

import java.util.List;

public class GradeCaluculator {
    private final Courses courses;


    public GradeCaluculator(List<Course> courses) {
        this.courses = new Courses(courses);
    }

    public GradeCaluculator(Courses courses) {
        this.courses = courses;
    }

    /**
     * 핵심 포인트
     */
    // 객체들을 포괄하는 타입에 적절한 책임을 할당하여 협력관계를 설계
    // 이수한 과목을 전달하여 평균학점 계산 요청 ----> 학점 계산기 --> (학점수 * 교과목 평점)의 합계 --> 과목(코스) 일급 컬렉션
    //                                                    -->   수강신청 총학점 수         --> 과목(코스) 일급 컬렉션
    public double calculateGrade() {
        // (학점수 * 교과목 평점) 의 합계
        double totalMultipliedCreditAndCourseGrade = courses.multipleiedCreditAndCourseGrade();
        // 수강신청 총학점 수
        int totalCompletedCredit = courses.calculateTotalCompletedCredit();

        return totalMultipliedCreditAndCourseGrade / totalCompletedCredit;
    }
}
