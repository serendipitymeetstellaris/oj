package org.example.friend.mapper.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.friend.domain.exam.vo.ExamVO;
import org.example.friend.domain.user.UserExam;

import java.util.List;


public interface UserExamMapper extends BaseMapper<UserExam> {

    List<ExamVO> selectUserExamList(Long userId);

}