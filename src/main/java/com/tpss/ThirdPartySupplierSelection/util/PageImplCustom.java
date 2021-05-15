package com.tpss.ThirdPartySupplierSelection.util;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class PageImplCustom<T> extends PageImpl {
    public PageImplCustom(List content, Pageable pageable, long total) {
	super(content, pageable, total);
    }

    public PageImplCustom(List content) {
	super(content);
    }

    public static PageImplCustom createPage(List content,Pageable pageable){
	int start = (int)pageable.getOffset();
	int end = (start + pageable.getPageSize()) > content.size() ? content.size() : (start + pageable.getPageSize());
	long total = content.size();
	return new PageImplCustom(content.subList(start,end), pageable, total);
    }
}
