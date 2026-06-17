package org.example.job.mapper.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.job.domain.user.UserScore;
import org.example.job.domain.user.UserSubmit;

import java.util.List;
import java.util.Set;

public interface UserSubmitMapper extends BaseMapper<UserSubmit> {

    List<UserScore> selectUserScoreList(Set<Long> examIdSet);
}
