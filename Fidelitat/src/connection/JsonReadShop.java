package connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

import com.digiteix.models.Shop;
import com.digiteix.models.ShopList;
import com.digiteix.models.TypeShop;

public class JsonReadShop extends AsyncTask<String, Void, ShopList> {

	private String url;
	private String jsonResult;
	private ShopList shopList;

	public JsonReadShop(ShopList shopList, String url) {
		this.shopList = shopList;
		this.url = url;
	}

	@Override
	protected ShopList doInBackground(String... params) {
		try {

			HttpClient httpclient = new DefaultHttpClient();
//			HttpPost httppost = new HttpPost("http://shopapp.digiteix.info/rodadeter/comercos_roda/api/json/shops");
			HttpPost httppost = new HttpPost(url);
			
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
			nameValuePairs.add(new BasicNameValuePair("modified_after", null));
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			
			HttpResponse response = httpclient.execute(httppost);
			jsonResult = inputStreamToString(response.getEntity().getContent()).toString();
			Log.i("prova", jsonResult);
			converteixPlaylist(jsonResult);
		}

		catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return shopList;
	}

	private StringBuilder inputStreamToString(InputStream is) {
		String rLine = "";
		StringBuilder answer = new StringBuilder();
		BufferedReader rd = new BufferedReader(new InputStreamReader(is));

		try {
			while ((rLine = rd.readLine()) != null) {
				answer.append(rLine);
			}
		}

		catch (IOException e) {
			// e.printStackTrace();
		}
		return answer;
	}

	private void converteixPlaylist(String jsonResultt) {

		try {
			JSONObject jsonResponse = new JSONObject(jsonResultt);
			JSONArray jsonMainNode = jsonResponse.optJSONArray("shops");

			for (int i = 0; i < jsonMainNode.length(); i++) {
				JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);

				int shopId = jsonChildNode.optInt("shopId");
				String shopName = jsonChildNode.optString("shopName");
				String description = jsonChildNode.optString("description");
				int typeId = jsonChildNode.optInt("typeId");
				String icon = jsonChildNode.optString("icon");
				String longitude = jsonChildNode.optString("longitude");
				String latitude = jsonChildNode.optString("latitude");
				String address = jsonChildNode.optString("address");
				String email = jsonChildNode.optString("email");
				String web = jsonChildNode.optString("web");
				String telephones_fix = jsonChildNode.optString("telephone");
				String telephones_mob = jsonChildNode.optString("cellphone");
				String zipCode = jsonChildNode.optString("zipCode");
				String city = jsonChildNode.optString("city");
				String coverSmallUrl = jsonChildNode.optString("coverSmallUrl");
				String coverBigUrl = jsonChildNode.optString("coverBigUrl");
				int parentId = jsonChildNode.optInt("parentId");
				String parentName = jsonChildNode.optString("parentName");
				String status = jsonChildNode.optString("status");
				String fb = jsonChildNode.optString("facebookUrl");
				String twitter = jsonChildNode.optString("twitterUrl");

				Shop shop = new Shop(shopId, shopName, description, typeId, longitude,
						latitude, address, email, web, telephones_fix, telephones_mob, fb, "navpreetsangha1", zipCode, city,
						coverSmallUrl, coverBigUrl, status);
				// Log.e("telephone", "tele" + shop.toString());
				// Log.e("song", (shopId + "|" + shopName + "|" + description
				// + "|" + typeId + "|" + icon + "|" + longitude + "|" +
				// latitude
				// + "|" + address + "|" + email + "|" + web
				// + "|" + telephones_fix + "|" + telephones_mob + "|" + zipCode
				// + "|" + city + "|" + coverSmallUrl + "|" + coverBigUrl + "|"
				// + parentId
				// + "|" + parentName + "|" + status).toString());
				// Add in playlisy

				shopList.addShop(shop);
				//Log.e("telephone", "tele" + shop.getTelephones_fix());
				// Log.e("shoddtelephone",
				// shopList.getArrayShop().get(0).getTelephones_fix());
			}

		} catch (JSONException e) {
			e.fillInStackTrace();
		}

	}

}