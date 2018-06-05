package com.sucheng.service.impl;

import com.sucheng.dao.GiftDAO;
import com.sucheng.dao.ProGiftDAO;
import com.sucheng.dos.GiftDO;
import com.sucheng.dto.GiftDTO;
import com.sucheng.service.AbstractBaseService;
import com.sucheng.service.GiftService;
import com.sucheng.vo.GiftVO;
import com.sucheng.vo.ProGiftVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.Serializable;

/**
 * GiftServiceImpl服务接口实现类<br/>
 *
 * 创建于2018-06-04<br/>
 *
 *
 * @version 1.0
 */
@Service(value = "giftService")
public class GiftServiceImpl extends AbstractBaseService implements GiftService {

    private GiftDAO giftDAO;
    private ProGiftDAO proGiftDAO;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(GiftVO giftVO, String proIds) {
        giftDAO.save(giftVO);
        String[] giftCount = proIds.split(",");
        for (String giftCounts : giftCount) {
            String[] pros = giftCounts.split("-");
            proGiftDAO.save(new ProGiftVO(null, Integer.valueOf(pros[0]),
                    giftVO.getId(), Float.valueOf(pros[1])));
        }
    }

    @Override
    public void removeById(Serializable id) {
        super.removeById(id);
        ProGiftVO proGiftVO = new ProGiftVO();
        proGiftVO.setGiftId(new Long((Long)id).intValue());
        proGiftDAO.remove(proGiftVO);
    }

    @Autowired
    public void setGiftDAO(GiftDAO giftDAO) {
        super.setBaseDAO(giftDAO);
        this.giftDAO = giftDAO;
    }
    @Autowired
    public void setProGiftDAO(ProGiftDAO proGiftDAO) {
        this.proGiftDAO = proGiftDAO;
    }

    @PostConstruct
    public void init() {
        super.init(GiftDO.class, GiftDTO.class);
    }
}
