package org.example.job.mapper.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.job.domain.user.UserExam;
import org.example.job.domain.user.UserScore;

import java.util.List;

public interface UserExamMapper extends BaseMapper<UserExam> {

    void updateUserScoreAndRank(List<UserScore> userScoreList);
}
