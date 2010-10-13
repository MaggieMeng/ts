package tice.weibo.Util;

import tice.weibo.HttpClient.TwitterClient;
import tice.weibo.List.TweetsListActivity;
import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class TwitterItem implements Parcelable {
    public String mScreenname;
    public String mTitle;
    public String mText;
    public long mTime;
    public String mSource;
    public String mTimeSource;
    public long mID;
    public String mReplyID;
    public String mImageurl;
    public boolean mFavorite;
    public boolean mFollowing;
    public boolean mLoading = false;
    public int mRead = 0;
    public String mAccount;
    public Bitmap mImage;
    public String mPicurl;
    public Bitmap mPic;
    public int mType = TwitterClient.HOME_HOME;
    //public Bitmap mImage;
    
    public String mRetweeted_Screenname;
    public String mRetweeted_Text;
    
    public void writeToParcel(Parcel dest, int flags) {
    	dest.writeString(mScreenname);
    	dest.writeString(mTitle);
    	dest.writeString(mText);
    	dest.writeLong(mTime);
    	dest.writeString(mSource);
    	dest.writeString(mTimeSource);
    	
    	dest.writeLong(mID);
    	dest.writeString(mReplyID);
    	dest.writeString(mImageurl);
    	
    	dest.writeInt((mFavorite == true) ? 1 : 0);
    	dest.writeInt((mFollowing == true) ? 1 : 0);
    	dest.writeInt((mLoading == true) ? 1 : 0);
    	
    	dest.writeString(mAccount);
    	dest.writeString(mPicurl);
    	
    	dest.writeString(mRetweeted_Screenname);
    	dest.writeString(mRetweeted_Text);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public static final Parcelable.Creator<TwitterItem> CREATOR
    = new Parcelable.Creator<TwitterItem>() {
        public TwitterItem createFromParcel(Parcel in) {
            return new TwitterItem(in);
        }

        public TwitterItem[] newArray(int size) {
            return new TwitterItem[size];
        }
    };
    
    private TwitterItem(Parcel in) {
    	mScreenname = in.readString();
        mTitle = in.readString();
        mText = in.readString();
        mTime = in.readLong();
        mSource = in.readString();
        mTimeSource = in.readString();
        
        mID = in.readLong();
        mReplyID = in.readString();
        mImageurl = in.readString();
        mImage = null;
        
        mFavorite = (in.readInt() == 1) ? true : false;
        mFollowing = (in.readInt() == 1) ? true : false;
        mLoading = (in.readInt() == 1) ? true : false;
        
        mAccount = in.readString();
        mPicurl = in.readString();
        mPic = null;
        
        mRetweeted_Screenname = in.readString();;
        mRetweeted_Text = in.readString();;

//    	mTimeSource = TweetsListActivity.CreateTimeSource(mTime, mSource);
    }

    public TwitterItem(int read, String screenname, String title, String text, long time, String source, long id, String replyid, boolean fav, boolean following, String iconuri, int type, String account, String picurl){
        try {
	    	mScreenname = screenname;
	        mTitle = title;
	        mTime = time;
	        mSource = source;
			mText = text;
	        mID = id;
	        mReplyID = replyid;
	        mFavorite = fav;
	        mFollowing = following;
	        mImageurl = iconuri;
	        mImage = null;
	        mRead = read;
	        mType = type;
	        mAccount = account;
	        mPicurl = picurl;
	        mPic = null;
	        
	        mRetweeted_Screenname = "";
	        mRetweeted_Text = "";

	    	mTimeSource = TweetsListActivity.CreateTimeSource(mTime, mSource);

		} catch (Exception e) {}
    }
    
    public TwitterItem(int read, String screenname, String title, String text, long time, String source, long id, String replyid, boolean fav, boolean following, String iconuri, int type, String account, String picurl, String Retweeted_Screenname, String Retweeted_Text){
        try {
	    	mScreenname = screenname;
	        mTitle = title;
	        mTime = time;
	        mSource = source;
			mText = text;
	        mID = id;
	        mReplyID = replyid;
	        mFavorite = fav;
	        mFollowing = following;
	        mImageurl = iconuri;
	        mImage = null;
	        mRead = read;
	        mType = type;
	        mAccount = account;
	        mPicurl = picurl;
	        mPic = null;
	        
	        mRetweeted_Screenname = Retweeted_Screenname;
	        mRetweeted_Text = Retweeted_Text;

	    	mTimeSource = TweetsListActivity.CreateTimeSource(mTime, mSource);

		} catch (Exception e) {}
    }


    public TwitterItem(TwitterItem obj){
        try {
	    	mScreenname = obj.mScreenname;
	        mTitle = obj.mTitle;
	        mTime = obj.mTime;
	        mSource = obj.mSource;
			mText = obj.mText;
	        mID = obj.mID;
	        mReplyID = obj.mReplyID;
	        mFavorite = obj.mFavorite;
	        mFollowing = obj.mFollowing;
	        mImageurl = obj.mImageurl;
	        mImage = null;
	        mRead = obj.mRead;
	        mTimeSource = obj.mTimeSource;
	        mType = obj.mType;
	        mAccount = obj.mAccount;
	        mPicurl = obj.mPicurl;
	        mPic = null;
	        
	        mRetweeted_Screenname = obj.mRetweeted_Screenname;
	        mRetweeted_Text = obj.mRetweeted_Text;
		} catch (Exception e) {}
    }

    public TwitterItem(){
        try {
	    	mScreenname = "";
	        mTitle = "";
	        mTime = 0;
	        mSource = "";
			mText = "";
	        mID = 0;
	        mReplyID = "";
	        mFavorite = false;
	        mFollowing = false;
	        mImageurl = "";
	        mImage = null;
	        mRead = 0;
	        mTimeSource = "";
	        mType = TwitterClient.HOME_HOME;
	        mAccount = "";
	        mPicurl = "";
	        mPic = null;
	        
	        mRetweeted_Screenname = "";
	        mRetweeted_Text = "";
		} catch (Exception e) {}
    }
}