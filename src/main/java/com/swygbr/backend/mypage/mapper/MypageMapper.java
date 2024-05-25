package com.swygbr.backend.mypage.mapper;

import com.swygbr.backend.mypage.vo.MypageVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MypageMapper {
    @Select("SELECT u.name AS userName, uc.title AS userCardName, " +
            "ROUND(AVG(CASE WHEN em.episode_complete_yn = 'Y' THEN 1 ELSE 0 END) * 100, 2) AS completionRate " +
            "FROM Users u " +
            "LEFT JOIN UserCard uc ON u.userCardFk = uc.id " +
            "LEFT JOIN TB_EPISODE_MAIN em ON u.id = em.user_id " +
            "WHERE u.id = #{userId} " +
            "GROUP BY u.id, u.name, uc.title")
    MypageVo findMyPageInfo(@Param("userId") Long userId);
}
