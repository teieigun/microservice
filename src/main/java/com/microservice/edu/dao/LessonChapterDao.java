package com.microservice.edu.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.microservice.edu.pojo.LessonChapterPojo;

@Repository
public class LessonChapterDao {

    /**
     * DAO
     */
    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * 可以免费观看的视频
     */
    public List<LessonChapterPojo> getChapterList(String email, String lesson_id) {

        String sql = "select ";
        sql = sql + "     lesson_id as LessonId ";
        sql = sql + "   , chapter_no as chapterNo ";
        sql = sql + "   , chapter_name as chapterName ";
        sql = sql + "   , '1' as testFlg ";
        sql = sql + "   , url as url ";
        sql = sql + " from ";
        sql = sql + "   lesson_chapter  ";
        sql = sql + " where lesson_id = ? ";
        sql = sql + "   and lesson_id in ";
        sql = sql + " (SELECT t2.lesson_id";
        sql = sql + "  FROM user_course_mapping t1 LEFT JOIN ";
        sql = sql + "       course_lesson_mapping t2 ";
        sql = sql + "    ON t1.course_id = t2.course_id";
        sql = sql + " WHERE t1.email =? ";
        sql = sql + " UNION ";
        sql = sql + "    SELECT t3.lesson_id ";
        sql = sql + "      FROM user_lession_mapping t3)";
        sql = sql + " order by ";
        sql = sql + "     lesson_id ";
        sql = sql + "   , chapter_no ";

        List<LessonChapterPojo> list = jdbcTemplate.query(sql, new Object[]{lesson_id, email}, new BeanPropertyRowMapper(LessonChapterPojo.class));
        return list;
    }

    public List<LessonChapterPojo> getChapterListForTest(String lesson_id) {

        String sql = "select ";
        sql = sql + "     lesson_id as LessonId ";
        sql = sql + "   , chapter_no as chapterNo ";
        sql = sql + "   , chapter_name as chapterName ";
        sql = sql + "   , test_flg as testFlg ";
        sql = sql + "   , url as url ";
        sql = sql + " from ";
        sql = sql + "   lesson_chapter  ";
        sql = sql + " where lesson_id = ? ";


        List<LessonChapterPojo> list = jdbcTemplate.query(sql, new Object[]{lesson_id}, new BeanPropertyRowMapper(LessonChapterPojo.class));
        return list;
    }



    /**
     * 可以免费观看的视频
     */
    public List<LessonChapterPojo> getOneChapter(String LessonId, String chapterNo) {

        String sql = "select ";
        sql = sql + "     lesson_id       ";
        sql = sql + "   , chapter_no          ";
        sql = sql + "   , chapter_name  ";
        sql = sql + "   , url      ";
        sql = sql + " from";
        sql = sql + "   lesson_chapter ";
        sql = sql + " where";
        sql = sql + "   lesson_id = ? ";
        sql = sql + " and chapter_no = ? ";

        List<LessonChapterPojo> list = jdbcTemplate.query(sql, new Object[]{LessonId, chapterNo}, new BeanPropertyRowMapper(LessonChapterPojo.class));
        return list;
    }

    public List<LessonChapterPojo> getOneChapter(String LessonId, String chapterNo,String email) {

        String sql = "select ";
        sql = sql + "      lesson_id ";
        sql = sql + "    , chapter_no";
        sql = sql + "    , chapter_name  ";
        sql = sql + "    , url";
        sql = sql + "  from lesson_chapter a";
        sql = sql + "  where lesson_id = ?";
        sql = sql + "    and chapter_no = ? ";
        sql = sql + "    and";
        sql = sql + "   exists(";
        sql = sql + "      select '1' ";
        sql = sql + "      FROM (select ";
        sql = sql + "            b.lesson_id,";
        sql = sql + "            b.chapter_no";
        sql = sql + "           from lesson_chapter b ";
        sql = sql + "          where b.lesson_id =?";
        sql = sql + "            and b.lesson_id in ";
        sql = sql + "              (select t2.lesson_id";
        sql = sql + "                 from user_course_mapping t1 ";
        sql = sql + "              left join course_lesson_mapping t2 ";
        sql = sql + "                        on t1.course_id = t2.course_id";
        sql = sql + "                 where t1.email = ? ";
        sql = sql + "               union ";
        sql = sql + "               select t3.lesson_id ";
        sql = sql + "                 from user_lession_mapping t3)";
        sql = sql + "           or b.test_flg='1') c ";
        sql = sql + "      where a.lesson_id=c.lesson_id and a.chapter_no=c.chapter_no)";

        List<LessonChapterPojo> list = jdbcTemplate.query(sql, new Object[]{LessonId, chapterNo,LessonId,email}, new BeanPropertyRowMapper(LessonChapterPojo.class));
        return list;
    }


    /**
     * 可以免费观看的视频
     */
    public int isTestAble(String LessonId) {

        String sql = "SELECT COUNT(1) AS cnt FROM lesson_chapter T1 WHERE  T1.TEST_FLG =1 AND T1.LESSON_ID=?";
        int count = jdbcTemplate.queryForObject(sql, new Object[] {LessonId},Integer.class);
        return count;
    }

}
