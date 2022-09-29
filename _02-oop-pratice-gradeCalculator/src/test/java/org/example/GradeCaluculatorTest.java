package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GradeCaluculatorTest {
    // 도메인을 구성하는 객체 고민
    // 학점계산기 도메인 : 이수한 과목, 학점 계산기

    // 동적인 객체를 정적인 타입으로 추상화해서 도메인 모델링 하기
    // 객체지향프로그래밍, 자료구조, 중국어회화 --> 과목(코스) 클래스

    /**
     * 핵심 포인트
     */
    // 객체들을 포괄하는 타입에 적절한 책임을 할당하여 협력관계를 설계
    // 이수한 과목을 전달하여 평균학점 계산 요청 ----> 학점 계산기 --> (학점수 * 교과목 평점)의 합계 --> 과목(코스)
    //                                                    -->   수강신청 총학점 수         --> 과목(코스)

    @DisplayName("평균 학점을 계산한다.")
    @Test
    void calculateGradeTest() {
        List<Course> courses = List.of(new Course("OOP",3,"A+"),
                new Course("자료구조",3,"A+"));

        GradeCaluculator gradeCaluculator = new GradeCaluculator(new Courses(courses));
        double gradeResult = gradeCaluculator.calculateGrade();

        assertThat(gradeResult).isEqualTo(4.5);
    }
}
