package zhaowei.com.servertest01;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LoadingDialog {

	public static Dialog dialog;
	
	public static void showDialog(Context context){
		dialog=new Dialog(context,R.style.dialog);
		dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.loading_dialog);
		dialog.setCanceledOnTouchOutside(false);
		dialog.show();
	}
	public static void disDialog(){
		dialog.dismiss();
	}
	

}
