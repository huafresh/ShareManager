package com.thinkive.android;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;

import com.mob.tools.utils.BitmapHelper;
import com.mob.tools.utils.ResHelper;

import java.util.ArrayList;
import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import com.thinkive.android.onekeyshare.CustomerLogo;
import com.thinkive.android.onekeyshare.OneKeyShare;
import com.thinkive.android.onekeyshare.OnekeyShareTheme;
import com.thinkive.android.onekeyshare.ShareContentCustomizeCallback;

/**
 * Created by hua on 2017/9/11.
 * ShareSDK的二次封装，目前支持分享: QQ（空间和好友）、微信（朋友圈，收藏和好友）、新浪微博、腾讯微博
 * 支持授权登录：QQ、微信、新浪微博
 */

public class ShareManager {

    private HashMap<String, Object> params;

    private ShareManager() {
        params = new HashMap<String, Object>();
        params.put("customers", new ArrayList<CustomerLogo>());
        params.put("hiddenPlatforms", new HashMap<String, String>());
    }

    public static ShareManager getInstance() {
        return new ShareManager();
    }


    /**
     * address是接收人地址，仅在信息和邮件使用，否则可以不提供
     */
    public ShareManager setAddress(String address) {
        params.put("address", address);
        return this;
    }

    /**
     * title标题，在印象笔记、邮箱、信息、微信（包括好友、朋友圈和收藏）、
     * 易信（包括好友、朋友圈）、人人网和QQ空间使用，否则可以不提供
     */
    public ShareManager setTitle(String title) {
        params.put("title", title);
        return this;
    }

    /**
     * titleUrl是标题的网络链接，仅在人人网和QQ空间使用，否则可以不提供
     */
    public ShareManager setTitleUrl(String titleUrl) {
        params.put("titleUrl", titleUrl);
        return this;
    }

    /**
     * text是分享文本，所有平台都需要这个字段
     */
    public ShareManager setText(String text) {
        params.put("text", text);
        return this;
    }

    /**
     * 获取text字段的值
     */
    public String getText() {
        return params.containsKey("text") ? String.valueOf(params.get("text")) : null;
    }

    /**
     * imagePath是本地的图片路径，除Linked-In外的所有平台都支持这个字段
     */
    public ShareManager setImagePath(String imagePath) {
        if (!TextUtils.isEmpty(imagePath)) {
            params.put("imagePath", imagePath);
            return this;
        }
        return this;
    }

    /**
     * imageUrl是图片的网络路径，新浪微博、人人网、QQ空间和Linked-In支持此字段
     */
    public ShareManager setImageUrl(String imageUrl) {
        if (!TextUtils.isEmpty(imageUrl)) {
            params.put("imageUrl", imageUrl);
        }
        return this;
    }

    /**
     * 设置分享图片的bitmap
     */
    public ShareManager setImageData(Bitmap imageData) {
        params.put("imageData", imageData);
        return this;
    }

    /**
     * url在微信（包括好友、朋友圈收藏）和易信（包括好友和朋友圈）中使用，否则可以不提供
     */
    public ShareManager setUrl(String url) {
        params.put("url", url);
        return this;
    }

    /**
     * filePath是待分享应用程序的本地路劲，仅在微信（易信）好友和Dropbox中使用，否则可以不提供
     */
    public ShareManager setFilePath(String filePath) {
        params.put("filePath", filePath);
        return this;
    }

    /**
     * comment是我对这条分享的评论，仅在人人网和QQ空间使用，否则可以不提供
     */
    public ShareManager setComment(String comment) {
        params.put("comment", comment);
        return this;
    }

    /**
     * site是分享此内容的网站名称，仅在QQ空间使用，否则可以不提供
     */
    public ShareManager setSite(String site) {
        params.put("site", site);
        return this;
    }

    /**
     * siteUrl是分享此内容的网站地址，仅在QQ空间使用，否则可以不提供
     */
    public ShareManager setSiteUrl(String siteUrl) {
        params.put("siteUrl", siteUrl);
        return this;
    }

    /**
     * foursquare分享时的地方名
     */
    public ShareManager setVenueName(String venueName) {
        params.put("venueName", venueName);
        return this;
    }

    /**
     * foursquare分享时的地方描述
     */
    public ShareManager setVenueDescription(String venueDescription) {
        params.put("venueDescription", venueDescription);
        return this;
    }

    /**
     * 分享地纬度，新浪微博、腾讯微博和foursquare支持此字段
     */
    public ShareManager setLatitude(float latitude) {
        params.put("latitude", latitude);
        return this;
    }

    /**
     * 分享地经度，新浪微博、腾讯微博和foursquare支持此字段
     */
    public ShareManager setLongitude(float longitude) {
        params.put("longitude", longitude);
        return this;
    }

    /**
     * 是否直接分享
     */
    public ShareManager setSilent(boolean silent) {
        params.put("silent", silent);
        return this;
    }

    /**
     * 设置编辑页的初始化选中平台
     */
    public ShareManager setPlatform(String platform) {
        params.put("platform", platform);
        return this;
    }

    /**
     * 设置KakaoTalk的应用下载地址
     */
    public ShareManager setInstallUrl(String installurl) {
        params.put("installurl", installurl);
        return this;
    }

    /**
     * 设置KakaoTalk的应用打开地址
     */
    public ShareManager setExecuteUrl(String executeurl) {
        params.put("executeurl", executeurl);
        return this;
    }

    /**
     * 设置微信分享的音乐的地址
     */
    public ShareManager setMusicUrl(String musicUrl) {
        params.put("musicUrl", musicUrl);
        return this;
    }

    /**
     * 设置自定义的外部回调
     */
    public ShareManager setCallback(PlatformActionListener callback) {
        params.put("callback", callback);
        return this;
    }

    /**
     * 返回操作回调
     */
    public PlatformActionListener getCallback() {
        return ResHelper.forceCast(params.get("callback"));
    }

    /**
     * 设置用于分享过程中，根据不同平台自定义分享内容的回调
     */
    public ShareManager setShareContentCustomizeCallback(ShareContentCustomizeCallback callback) {
        params.put("customizeCallback", callback);
        return this;
    }

    /**
     * 自定义不同平台分享不同内容的回调
     */
    public ShareContentCustomizeCallback getShareContentCustomizeCallback() {
        return ResHelper.forceCast(params.get("customizeCallback"));
    }

    /**
     * 设置自己图标和点击事件，可以重复调用添加多次
     */
    public ShareManager setCustomerLogo(Bitmap logo, String label, View.OnClickListener ocl) {
        CustomerLogo cl = new CustomerLogo();
        cl.logo = logo;
        cl.label = label;
        cl.listener = ocl;
        ArrayList<CustomerLogo> customers = ResHelper.forceCast(params.get("customers"));
        customers.add(cl);
        return this;
    }

    /**
     * 设置一个总开关，用于在分享前若需要授权，则禁用sso功能
     */
    public ShareManager disableSSOWhenAuthorize() {
        params.put("disableSSO", true);
        return this;
    }

    /**
     * 设置视频网络地址
     */
    public ShareManager setVideoUrl(String url) {
        params.put("url", url);
        params.put("shareType", Platform.SHARE_VIDEO);
        return this;
    }

    /**
     * 添加一个隐藏的platform
     */
    public ShareManager addHiddenPlatform(String platform) {
        HashMap<String, String> hiddenPlatforms = ResHelper.forceCast(params.get("hiddenPlatforms"));
        hiddenPlatforms.put(platform, platform);
        return this;
    }

    /**
     * 设置一个将被截图分享的View , surfaceView是截不了图片的
     */
    public ShareManager setViewToShare(View viewToShare) {
        try {
            Bitmap bm = BitmapHelper.captureView(viewToShare, viewToShare.getWidth(), viewToShare.getHeight());
            params.put("viewToShare", bm);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * 腾讯微博分享多张图片
     */
    public ShareManager setImageArray(String[] imageArray) {
        params.put("imageArray", imageArray);
        return this;
    }

    /**
     * 设置在执行分享到QQ或QZone的同时，分享相同的内容腾讯微博
     */
    public ShareManager setShareToTencentWeiboWhenPerformingQQOrQZoneSharing() {
        params.put("isShareTencentWeibo", true);
        return this;
    }

    /**
     * 设置分享界面的样式，目前只有一种，不需要设置
     */
    public ShareManager setTheme(OnekeyShareTheme theme) {
        params.put("theme", theme.getValue());
        return this;
    }

    /**
     * 设置分享类型
     */
    public ShareManager setShareType(int shareType) {
        params.put("shareType", shareType);
        return this;
    }


    /**
     * 执行分享。
     * <p>
     * 使用方法：
     * 先拿到本类实例，再调用各种setter方法设置参数，最后调用此方法即可。
     */
    public void show(Context context) {
        OneKeyShare.show(context, params);
    }

    private String TEST_IMAGE;
    private final String FILE_NAME = "/picShare.jpg";


    /**
     * 分享文本。
     *
     * @param context  上下文
     * @param title    标题
     * @param text     内容
     * @param titleUrl QQ空间使用，为分享后的内容链接
     * @param url      新浪微博使用，为分享后的内容链接
     * @param listener 回调
     */
    public void shareText(Context context, String title, String text, String titleUrl, String url,
                          PlatformActionListener listener) {
        disableSSOWhenAuthorize();
        setTitle(title);
        setText(text);
        setTitleUrl(titleUrl);
        setUrl(url);
        setCallback(listener);
        setShareType(Platform.SHARE_TEXT);
        show(context);
    }

    /**
     * 分享图片
     *
     * @param context     上下文
     * @param text        图片说明
     * @param imageUrl    图片本地地址 / 网络地址
     * @param imageData   图片的Bitmap
     * @param imageArrays 腾讯微博分享多张图片
     * @param listener    回调
     */
    public void shareImage(Context context, String text, String imageUrl, Bitmap imageData,
                           String[] imageArrays, PlatformActionListener listener) {
        setText(text);
        if (imageUrl.startsWith("http")) {
            setImageUrl(imageUrl);
        } else {
            setImagePath(imageUrl);
        }
        setImageData(imageData);
        setImageArray(imageArrays);
        setCallback(listener);
        setShareType(Platform.SHARE_IMAGE);
        show(context);
    }


    // TODO: 2017/9/11 分享app
    public void shareApp(Context context, String text, String title, String imagePath, String filePath,
                         PlatformActionListener listener) {
        setText(text);
        setTitle(title);
        setImagePath(imagePath);
        setFilePath(filePath);
        show(context);
    }

    // TODO: 2017/9/11 分享截图


}
