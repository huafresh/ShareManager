/*
 * 官网地站:http://www.mob.com
 * 技术支持QQ: 4006852216
 * 官方微信:ShareSDK   （如果发布新版本的话，我们将会第一时间通过微信将版本更新内容推送给您。如果使用过程中有任何问题，也可以通过微信与我们取得联系，我们将会在24小时内给予回复）
 *
 * Copyright (c) 2013年 mob.com. All rights reserved.
 */

package com.thinkive.android.onekeyshare;

import android.content.Context;

import com.mob.MobSDK;
import com.mob.tools.utils.ResHelper;

import java.util.ArrayList;
import java.util.HashMap;

import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;

/**
* 快捷分享的入口
*/
public class OneKeyShare {

	@SuppressWarnings("unchecked")
	public static void show(Context context, HashMap<String, Object> params) {
		HashMap<String, Object> shareParamsMap = new HashMap<String, Object>();
		shareParamsMap.putAll(params);

		MobSDK.init(context.getApplicationContext());

		// 打开分享菜单的统计
		ShareSDK.logDemoEvent(1, null);

		int iTheme = 0;
		try {
			iTheme = ResHelper.parseInt(String.valueOf(shareParamsMap.remove("theme")));
		} catch (Throwable t) {}
		OnekeyShareTheme theme = OnekeyShareTheme.fromValue(iTheme);
		OnekeyShareThemeImpl themeImpl = theme.getImpl();

		themeImpl.setShareParamsMap(shareParamsMap);
		themeImpl.setDialogMode(shareParamsMap.containsKey("dialogMode") ? ((Boolean) shareParamsMap.remove("dialogMode")) : false);
		themeImpl.setSilent(shareParamsMap.containsKey("silent") ? ((Boolean) shareParamsMap.remove("silent")) : false);
		themeImpl.setCustomerLogos((ArrayList<CustomerLogo>) shareParamsMap.remove("customers"));
		themeImpl.setHiddenPlatforms((HashMap<String, String>) shareParamsMap.remove("hiddenPlatforms"));
		themeImpl.setPlatformActionListener((PlatformActionListener) shareParamsMap.remove("callback"));
		themeImpl.setShareContentCustomizeCallback((ShareContentCustomizeCallback) shareParamsMap.remove("customizeCallback"));
		if (shareParamsMap.containsKey("disableSSO") ? ((Boolean) shareParamsMap.remove("disableSSO")) : false) {
			themeImpl.disableSSO();
		}

		themeImpl.show(context.getApplicationContext());
		
	}
}
