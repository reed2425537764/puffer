package cn.syq.puffer.manager.model.api;

import lombok.Builder;
import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 * @Author: shiyuqin
 * @Date: 2022/7/18 21:00
 */
@Data
@Builder
public class PageVo<T> {

    private int pageNo;

    private int pageSize;

    private int totalPage;

    private int totalSize;

    private List<T> data = Collections.emptyList();
}
