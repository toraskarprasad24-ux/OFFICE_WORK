//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.concerto.sprinklr.application.util;

import com.concerto.sprinklr.application.init.ServerInitializer;
import com.concerto.sprinklr.application.req.bean.ContextParams;
import java.util.Comparator;

public class ComapareByMessageid implements Comparator<ContextParams> {
	public ComapareByMessageid() {
	}

	public int compare(ContextParams o1, ContextParams o2) {
		return ServerInitializer.reqsorttype == 1 ? Integer.parseInt(o2.getMessage_id()) - Integer.parseInt(o1.getMessage_id()) : Integer.parseInt(o1.getMessage_id()) - Integer.parseInt(o2.getMessage_id());
	}
}
