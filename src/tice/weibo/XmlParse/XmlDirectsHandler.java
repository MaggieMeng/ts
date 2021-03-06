package tice.weibo.XmlParse;

import java.util.Date;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import tice.weibo.Util.TweetsData;
import tice.weibo.Util.TwitterItem;
import android.text.Html;

public class XmlDirectsHandler extends DefaultHandler {

	private int mType;
    private boolean in_direct_message = false; 
    private boolean in_sender = false; 
    private boolean in_recipient = false;
    private boolean in_error = false;
    
    private StringBuilder builder;
    private TwitterItem mItem = null;
	private TweetsData mTweetsData = null;
    
	public TweetsData GetParsedData() { 
		return mTweetsData; 
	} 
    
	public XmlDirectsHandler(int type){
		mType = type;
	}
	
    @Override 
    public void startDocument() throws SAXException { 
         mTweetsData = new TweetsData(); 
    } 

    @Override 
    public void endDocument() throws SAXException { 
         // Nothing to do 
    } 
    
    @Override 
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException { 
    	if (localName.equals("direct_message")){
        	in_direct_message = true;
        	mItem = new TwitterItem();
        	mItem.mType = mType;
        	builder = new StringBuilder();
        }else if (localName.equals("sender")){
        	in_sender = true;
        }else if (localName.equals("recipient")){
        	in_recipient = true;
        }else if (localName.equals("error")){
        	in_error = true;
        	builder = new StringBuilder();
        }
    } 
     
    @Override 
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException { 
        
    	if (localName.equals("direct_message")){
        	in_direct_message = false;
        	mTweetsData.items.add(mItem);
        } else if (localName.equals("sender")){
        	in_sender = false;
        } else if (localName.equals("recipient")){
        	in_recipient = false;
        }else if (localName.equals("error")){
        	in_error = false;
        	mTweetsData.mError = builder.toString();
        	builder.setLength(0);
        }
    	
    	if(in_direct_message && !in_recipient){

    		String body = builder.toString().trim();

    		if (localName.equals("created_at") && in_sender == false){
	        	mItem.mTime = Date.parse(body);
	        } else if (localName.equals("id") && in_sender == false){
	        	mItem.mID = Long.valueOf(body);
	        } else if (localName.equals("text") && in_sender == false){
	        	mItem.mText = String.format("%s", Html.fromHtml(body));
	        } else if (localName.equals("source") && in_sender == false){
	        	mItem.mSource = String.format("%s",Html.fromHtml(body));
	        } else if (localName.equals("in_reply_to_status_id") && in_sender == false){
	        	mItem.mReplyID = body;
	        } else if (localName.equals("favorited") && in_sender == false){
	        	mItem.mFavorite = Boolean.parseBoolean(body);
	        } else if (localName.equals("screen_name") && in_sender == true){
	        	mItem.mScreenname = body;
	        } else if (localName.equals("name") && (in_sender == true)){
	        	mItem.mTitle = body;
	        } else if (localName.equals("profile_image_url") && in_sender == true ){
	        	mItem.mImageurl = body;
	        }
	    
	    	builder.setLength(0);
	    }
    } 
     
    @Override 
	public void characters(char ch[], int start, int length) { 
    	if (in_direct_message && !in_recipient || in_error == true){
    		if(ch[start] == '\r' || ch[start] == '\n') return;
    		builder.append(ch, start, length);
    	}
    } 
}
