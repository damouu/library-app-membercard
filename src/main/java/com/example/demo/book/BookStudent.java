package com.example.demo.book;

import com.example.demo.student_id_card.StudentIdCard;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class BookStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id_card_id")
    private StudentIdCard studentIdCard;

    private Date borrow_request_date;
    private Date borrow_start_date;
    private Date borrow_end_date;
    private Date borrow_return_date;
    private boolean granted_borrow_extend;

    public BookStudent(StudentIdCard studentIdCard, Date borrow_request_date, Date borrow_start_date, Date borrow_end_date, Date borrow_return_date, boolean granted_borrow_extend) {
        this.studentIdCard = studentIdCard;
        this.borrow_request_date = borrow_request_date;
        this.borrow_start_date = borrow_start_date;
        this.borrow_end_date = borrow_end_date;
        this.borrow_return_date = borrow_return_date;
        this.granted_borrow_extend = granted_borrow_extend;
    }


}
