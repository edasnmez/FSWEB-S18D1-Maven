package com.workintech.sqldmljoins.repository;

import com.workintech.sqldmljoins.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OgrenciRepository extends JpaRepository<Ogrenci, Long> {


    //Kitap alan öğrencilerin öğrenci bilgilerini listeleyin..
    String QUESTION_2 = "select o.* from ogrenci o\n" +
            "inner join islem i on  o.ogrno =i.ogrno";
    @Query(value = QUESTION_2, nativeQuery = true)
    List<Ogrenci> findStudentsWithBook();


    //Kitap almayan öğrencileri listeleyin.
    String QUESTION_3 = "SELECT o.*\n" +
            "FROM ogrenci o\n" +
            "LEFT JOIN islem i ON o.ogrno = i.ogrno\n" +
            "WHERE i.ogrno IS NULL";
    @Query(value = QUESTION_3, nativeQuery = true)
    List<Ogrenci> findStudentsWithNoBook();

    //10A veya 10B sınıfındaki öğrencileri sınıf ve okuduğu kitap sayısını getirin.
    String QUESTION_4 = "SELECT \n" +
            "    o.sinif,\n" +
            "    COUNT(i.kitapno)\n" +
            "FROM \n" +
            "    ogrenci o\n" +
            "JOIN \n" +
            "    islem i ON o.ogrno = i.ogrno\n" +
            "WHERE \n" +
            "    o.sinif IN ('10A', '10B')\n" +
            "GROUP BY \n" +
            "    o.sinif";
    @Query(value = QUESTION_4, nativeQuery = true)
    List<KitapCount> findClassesWithBookCount();

    //Öğrenci tablosundaki öğrenci sayısını gösterin
    String QUESTION_5 = "SELECT COUNT(ogrno) AS ogrenci_sayisi\n" +
            "FROM ogrenci;";
    @Query(value = QUESTION_5, nativeQuery = true)
    Integer findStudentCount();

    //Öğrenci tablosunda kaç farklı isimde öğrenci olduğunu listeleyiniz.
    String QUESTION_6 = "SELECT COUNT(DISTINCT ad) AS farkli_isim_sayisi\n" +
            "FROM ogrenci;";
    @Query(value = QUESTION_6, nativeQuery = true)
    Integer findUniqueStudentNameCount();

    //--İsme göre öğrenci sayılarının adedini bulunuz.
    //--Ali: 2, Mehmet: 3
    String QUESTION_7 = "SELECT ad,count(ad)\n" +
            "FROM ogrenci\n" +
            "GROUP BY ad;";
    @Query(value = QUESTION_7, nativeQuery = true)
    List<StudentNameCount> findStudentNameCount();

    //Her sınıftaki öğrenci sayısını bulunuz.
    String QUESTION_8 = "SELECT sinif,count(ogrno)\n" +
            "FROM ogrenci\n" +
            "GROUP BY sinif;";
    @Query(value = QUESTION_8, nativeQuery = true)
    List<StudentClassCount> findStudentClassCount();

    //Her öğrencinin ad soyad karşılığında okuduğu kitap sayısını getiriniz.
    String QUESTION_9 = "SELECT \n" +
            "    o.ad,\n" +
            "    o.soyad,\n" +
            "    COUNT(i.kitapno) AS kitap_sayisi\n" +
            "FROM \n" +
            "    ogrenci o INNER JOIN \n" +
            "    islem i ON o.ogrno = i.ogrno\n" +
            "GROUP BY \n" +
            "    o.ogrno, o.ad, o.soyad\n" +
            "ORDER BY kitap_sayisi\n";
    @Query(value = QUESTION_9, nativeQuery = true)
    List<StudentNameSurnameCount> findStudentNameSurnameCount();
}
