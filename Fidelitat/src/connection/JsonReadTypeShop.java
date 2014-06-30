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

import com.digiteix.models.TypeShop;
import com.digiteix.models.TypeShopList;

public class JsonReadTypeShop extends AsyncTask<String, Void, TypeShopList> {

	private String url;
	private String search1;
	private String jsonResult;
	private TypeShopList typeShoplist;

	public JsonReadTypeShop(TypeShopList typeShoplist, String url) {
		this.typeShoplist = typeShoplist;
		this.url = url;
	}

	@Override
	protected TypeShopList doInBackground(String... params) {
		try {

			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(url);

			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
			nameValuePairs.add(new BasicNameValuePair("search", search1));
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

		return typeShoplist;
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
			JSONArray jsonMainNode = jsonResponse.optJSONArray("markerTypes");

			for (int i = 0; i < jsonMainNode.length(); i++) {
				JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);

				int markerTypeId = jsonChildNode.optInt("markerTypeId");
				String markerTypeName = jsonChildNode.optString("markerTypeName");
				String description = jsonChildNode.optString("description");
				String icon = jsonChildNode.optString("icon");
				String status = jsonChildNode.optString("status");


				
				TypeShop typeShop = new TypeShop(markerTypeId, markerTypeName, description, status, icon);
				typeShoplist.addTypeShop(typeShop);
//				shopList.addShop(shop);
				Log.e("telephone", "tele" + typeShop.getName());
			}

		} catch (JSONException e) {
			e.fillInStackTrace();
		}

	}

}