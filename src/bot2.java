import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import twitter4j.GeoLocation;
import twitter4j.GeoQuery;
import twitter4j.Place;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.api.PlacesGeoResources;
import twitter4j.conf.ConfigurationBuilder;

public class bot2{

	private Twitter twitter;
	private ArrayList<Status> searchList = new ArrayList<Status>();
	private ArrayList<String> replies = new ArrayList<String>();
	
	public bot2() {
		ConfigurationBuilder cb = new ConfigurationBuilder();//access the twitter API using your twitter4j.properties file
		cb.setDebugEnabled(true).setOAuthConsumerKey("n0Mal8t2KJWiFKWld4AtDjdd2")
				.setOAuthConsumerSecret("r2wJfgL8YDy6kFFqkMVuO0UaRX81UPSkKKAlHXzg28JzOA9xPU")
				.setOAuthAccessToken("841699158989905922-kvDL07g8G0oqDC6aFHOID2blUrIoyqE")
				.setOAuthAccessTokenSecret("zrvCWpdxgvlnJRkeVCbqokAz38K2UAVfxaN3aLRDEX2ly");
		TwitterFactory tf = new TwitterFactory(cb.build());
		twitter = tf.getInstance();		
	}
	
	public void updateStatus(String str) throws TwitterException{
		Status status = twitter.updateStatus(str);
	}
	
	private void searchAll(String str) throws TwitterException {
		Query query = new Query(str);
		QueryResult result = twitter.search(query);
		
		for(Status tweet: result.getTweets()) {
			
			if(tweet.isRetweeted()) continue;
			//if(!(tweet.getUser().getScreenName()).equals("NASA")) continue;
			generateTweet(tweet.getText()); break;
			//searchList.add(tweet);
		}
		//reply(searchList.get(0));
		//System.out.println(searchList.get(0).getUser().getScreenName());
		//locate(searchList.get(0).getUser().getLocation());
		//generateTweet(searchList.get(0).getText());
	}

	private void getUserTweet() {
		
	}
	
	private void generateTweet(String s) throws TwitterException {
		Random rand = new Random();
		String myTweet = "";
		String newS = s.toString();
		Scanner in = new Scanner(newS);
		
		ArrayList<String> starCol = new ArrayList<String>();
		starCol.add("  .");starCol.add("˚  ");starCol.add("+");starCol.add("⋆  ");starCol.add("*");
		starCol.add("✧");starCol.add("✷");starCol.add("✵");starCol.add("✵ ");starCol.add("☼");starCol.add("☾");
		
		ArrayList<String> spaceCol = new ArrayList<String>();
		spaceCol.add(" ");spaceCol.add("  ");spaceCol.add("	  ");
		spaceCol.add("	   ");spaceCol.add("    ");spaceCol.add("\n");spaceCol.add("    "+"\n");
		
		int star = 0;
		int space = spaceCol.size();
		while(in.hasNext() && myTweet.length() < 140) {
			String word = in.next();
			myTweet += spaceCol.get(rand.nextInt(spaceCol.size()));
			if (word.length() > starCol.size()) {
				myTweet += starCol.get(rand.nextInt(starCol.size()));
			} else {
				myTweet += starCol.get(rand.nextInt(word.length()));
			}
			myTweet += spaceCol.get(rand.nextInt(spaceCol.size() - 1));
			myTweet += starCol.get(rand.nextInt(word.length()));

			String testtweet = myTweet;
			System.out.println(testtweet);
		}
		in.close();
		updateStatus(myTweet);
	}
	private void reply(Status tweet) throws TwitterException {		
		Query query = new Query("");
		QueryResult result = twitter.search(query);
		Random rand = new Random();
		replies.add(" very neat! go u!");replies.add(" congrats, you deserve it!");replies.add(" way to go bud!");
		int replyNum = rand.nextInt(replies.size());
		
		StatusUpdate statusupd = new StatusUpdate("@" + tweet.getUser().getScreenName() + replies.get(replyNum));
		statusupd.inReplyToStatusId(tweet.getId());
		Status status = twitter.updateStatus(statusupd);
	}

	private void locate(String str) throws TwitterException {
		Place plc = pgr.getGeoDetails(str);
		System.out.println(plc+"");
//		GeoLocation gl = new GeoLocation(lat, lon);
//		System.out.println(gl.toString());
//		searchLocation(gl);
	}
	private PlacesGeoResources pgr;
	private void searchLocation(GeoLocation gl) throws TwitterException {
		GeoQuery geoq = new GeoQuery(gl);
	
	//	ResponseList<Place> resL =  pgr.reverseGeoCode(geoq);
		String ip = geoq.getIp();
		System.out.println(ip);
		//GeoLocation[][] geoArr = resL.get(0).getGeometryCoordinates();
		
		//System.out.println(Arrays.toString(geoArr));
	}
	
	
	public static void main(String... args) throws TwitterException, InterruptedException {
		//int hr = 0;
		// while (hr < 24) {
		bot2 bot = new bot2();
		ArrayList<String> toSearch = new ArrayList<String>();
		//toSearch.add("excited");
		//toSearch.add(" pumped");
		//toSearch.add(" happy");
		
		
		//Random r = new Random();
		//int toSearchNum = r.nextInt(toSearch.size());
		//bot.updateStatus("Hello!! " + hr + " hours have passed since first update.");
		//bot.searchAll(toSearch.get(toSearchNum));
		//hr++;
	//	bot.updateStatus("☀ 🌌");
		//bot.locate(51.5033640,-0.1276250);
		System.out.println("Done. Going to sleep.");
		// Thread.sleep(60 * 60 * 1000);

		// }
	}
}