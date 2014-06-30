package adapter;

import DAO.ShopList_ShopDAO;
import DAO.ShopType_ShopTypeDAO;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ImageDownload.ImageLoader;
import com.digiteix.models.Shop;
import com.digiteix.models.ShopList;
import com.digiteix.models.TypeShop;
import com.digiteix.models.TypeShopList;
import com.fidelitat.R;

public class SimpleAdapterAllshops extends ArrayAdapter<Shop> {

	private ShopList shopList;
	private Context context;
	private ImageLoader imgLoader;
	private TypeShop shopType;
	private ShopType_ShopTypeDAO shopType_shopTypeDAO;

	public SimpleAdapterAllshops(Context context, ShopList itemList) {
		super(context, R.layout.list_view_shops_layout);
		this.shopList = itemList;
		this.context = context;
	}


	public int getCount() {
		if (shopList != null)
			return shopList.getArrayShop().size();
		return 0;
	}

	public Shop getItem(int position) {
		if (shopList != null)
			return shopList.getArrayShop().get(position);
		return null;
	}

	public long getItemId(int position) {
		if (shopList != null)
			return shopList.getArrayShop().get(position).hashCode();
		return 0;
	}

	@Override
	public View getView(int position, View v, ViewGroup parent) {

		
		if (v == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.list_view_shops_layout, null);
		}

		Shop shop = shopList.getArrayShop().get(position);
		imgLoader = new ImageLoader(context);

		TextView shopName = (TextView) v.findViewById(R.id.txtAdaptarShopNameList);
		TextView shopDesc = (TextView) v.findViewById(R.id.txtAdaptarShopDescripcionList);
		ImageView imgTyp = (ImageView) v.findViewById(R.id.imgAdaptarSelectShopType);
		ImageView imgShopLogo = (ImageView) v.findViewById(R.id.imgAdaptarShopListLogo);

		shopName.setText(shop.getShopName());
		shopDesc.setText(shop.getDesceiption());
		
		shopType_shopTypeDAO = new ShopType_ShopTypeDAO(getContext());
		shopType = new TypeShop();
		shopType = shopType_shopTypeDAO.getTodos(shop.getId_type());

		
		
		String urll = shop.getCoverBigUrl();
		String name = shop.getShopName();

		imgLoader.DisplayImage(urll, imgShopLogo, name);

		return v;

	}

	public ShopList getShopList() {
		return shopList;
	}

	public void setShopList(ShopList shopList) {
		this.shopList = shopList;
	}

}
