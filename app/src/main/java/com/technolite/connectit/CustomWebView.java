package com.technolite.connectit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.CookieManager;
import android.webkit.URLUtil;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Objects;
import java.util.Random;

public class CustomWebView extends AppCompatActivity {

    public static final int REQUEST_SELECT_FILE = 100;
    private final static int FILECHOOSER_RESULTCODE = 1;
    final String downloadDirectory = "/Download/eazypost";
    public ValueCallback<Uri[]> uploadMessage;
    // UI
    //private SwipeRefreshLayout refreshLayout;
    private WebView webView;
    private ImageView goBack, goNext;

    private ValueCallback<Uri> mUploadMessage;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_web_view);

        // Init
        //refreshLayout = findViewById(R.id.refreshLayout);

        goBack = findViewById(R.id.ic_goBack);
        goNext = findViewById(R.id.ic_goNext);

        webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSavePassword(true);
        webView.getSettings().setSupportZoom(false);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setMediaPlaybackRequiresUserGesture(false);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAllowContentAccess(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setBlockNetworkImage(false);
        webView.getSettings().setAllowUniversalAccessFromFileURLs(true);

        // Web Client
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("mailto:") || url.startsWith("tel:") || url.startsWith("whatsapp:")  ) {

                    //startActivity(new Intent(getApplicationContext(),Aboutus.class));

                    return true;

                }
                return false;

            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                //if (!refreshLayout.isRefreshing()) refreshLayout.setRefreshing(true);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //if (refreshLayout.isRefreshing()) refreshLayout.setRefreshing(false);
            }
        });

        // Web Chrome Client
        webView.setWebChromeClient(new WebChromeClient() {
            protected void openFileChooser(ValueCallback uploadMsg, String acceptType) {
                mUploadMessage = uploadMsg;
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("image/*");
                startActivityForResult(Intent.createChooser(i, "File Browser"), FILECHOOSER_RESULTCODE);
            }

            public boolean onShowFileChooser(WebView mWebView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
                if (uploadMessage != null) {
                    uploadMessage.onReceiveValue(null);
                    uploadMessage = null;
                }
                uploadMessage = filePathCallback;
                Intent intent = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    intent = fileChooserParams.createIntent();
                }
                try {
                    startActivityForResult(intent, REQUEST_SELECT_FILE);
                } catch (ActivityNotFoundException e) {
                    uploadMessage = null;
                    return false;
                }
                return true;
            }

            protected void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
                mUploadMessage = uploadMsg;
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "File Browser"), FILECHOOSER_RESULTCODE);
            }

            protected void openFileChooser(ValueCallback<Uri> uploadMsg) {
                mUploadMessage = uploadMsg;
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("image/*");
                startActivityForResult(Intent.createChooser(i, "File Chooser"), FILECHOOSER_RESULTCODE);
            }
        });

        // Download Listener
        //Latest code


       /* webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                try {
                    if (url.startsWith("data:")) {
                        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
                        request.setMimeType(mimetype);
                        String cookies = CookieManager.getInstance().getCookie(url);
                        request.addRequestHeader("cookie", cookies);
                        request.addRequestHeader("User-Agent", userAgent);
                        request.setDescription("Downloading file...");
                        request.setTitle(URLUtil.guessFileName(url, contentDisposition, mimetype));
                        request.allowScanningByMediaScanner();
                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

                        //Set the destination to the external storage
                        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "/eazypost/" + URLUtil.guessFileName(url, contentDisposition, mimetype));
                        DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                        dm.enqueue(request);
                        //oast.makeText(getApplicationContext(), "Downloading File", Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(), "File get downloaded", Toast.LENGTH_LONG).show();

                    }
                    else{
                        Toast.makeText(getApplicationContext(), "File Not Downloading", Toast.LENGTH_LONG).show();

                    }
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "This file can't get Downloaded", Toast.LENGTH_LONG).show();

                }
            }
        });*/


        //juna code
        webView.setDownloadListener((url, userAgent, contentDisposition, mimeType, contentLength) -> {
            try {
                if (url.startsWith("data:")) {  //when url is base64 encoded data
                    byte[] bytes = Base64.decode(url.split(",")[1], Base64.NO_WRAP);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    Uri images;
                    ContentResolver contentResolver=getContentResolver();

                    Random rndm = new Random();
                    int mnmbr = 100000;

                    int rndNumber = rndm.nextInt(mnmbr) + 10;

                    if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.Q){
                        images= MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY);
                    }
                    else{
                        images=MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                    }
                    ContentValues contentValues = new ContentValues();

                    contentValues.put(MediaStore.Images.Media.DISPLAY_NAME, System.currentTimeMillis() + "/eazypost" + rndNumber + ".jpg");
                    contentValues.put(MediaStore. Images.Media.MIME_TYPE, "images/*");

                    Uri uri= contentResolver.insert(images, contentValues);
                    try{

                        OutputStream outputStream = contentResolver.openOutputStream(Objects.requireNonNull(uri));

                        bitmap.compress (Bitmap.CompressFormat.JPEG, 100, outputStream);

                        Objects.requireNonNull(outputStream);
                        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
                        request.setDestinationUri(uri);
                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                        DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                        dm.enqueue(request);


                    }catch (Exception e){
                        //  Toast.makeText(this, "Image not saved " , Toast.LENGTH_SHORT).show();
                        //Log.e("image", e.getMessage());

                    }

                    File folder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "eazypost");
                    if (!folder.exists()) {
                        folder.mkdirs();
                    }
                    File file = new File(Environment.getExternalStorageDirectory() + "/Download/eazypost/eazypost" + rndNumber + ".png");
                    FileOutputStream fileout = new FileOutputStream(file);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileout);
                    outputWriter.close();


                    Toast.makeText(this, "Image Saved at " , Toast.LENGTH_SHORT).show();


                } else {
                    DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
                    request.setMimeType(mimeType);

                    String cookies = CookieManager.getInstance().getCookie(url);
                    request.addRequestHeader("cookie", cookies);
                    request.addRequestHeader("User-Agent", userAgent);
                    request.setDescription("Downloading file...");
                    request.setTitle(URLUtil.guessFileName(url, contentDisposition, mimeType));
                    request.allowScanningByMediaScanner();
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, URLUtil.guessFileName(url, contentDisposition, mimeType));
                    Log.d("Path->", Environment.DIRECTORY_DOWNLOADS + "/eazypost/" + URLUtil.guessFileName(url, contentDisposition, mimeType));
                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,
                            "/eazypost/" + URLUtil.guessFileName(url, contentDisposition, mimeType));
                    DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                    dm.enqueue(request);
                    Toast.makeText(getApplicationContext(), "Downloading File", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        //refreshLayout.setOnRefreshListener(() -> webView.loadUrl(webView.getUrl()));

        goBack.setOnClickListener(v -> {
            if (webView.canGoBack()) webView.goBack();
        });

        goNext.setOnClickListener(v -> {
            if (webView.canGoForward()) webView.goForward();
        });

        bindWebView();
    }

    private void bindWebView() {
        try {
            webView.loadUrl(getIntent().getStringExtra("LinkToOpen"));
        } catch (Exception exc) {
            Toast.makeText(this, exc.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (requestCode == REQUEST_SELECT_FILE) {
                if (uploadMessage == null)
                    return;
                uploadMessage.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(resultCode, intent));
                uploadMessage = null;
            }
        } else if (requestCode == FILECHOOSER_RESULTCODE) {
            if (null == mUploadMessage)
                return;
            Uri result = intent == null || resultCode != MainActivity.RESULT_OK ? null : intent.getData();
            mUploadMessage.onReceiveValue(result);
            mUploadMessage = null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.refresh_menu, menu);
        Drawable drawable = menu.getItem(0).getIcon();
        if(drawable != null) {
            drawable.mutate();
            drawable.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        }
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_refresh:
                webView.reload();
                //Toast.makeText(this,"hit",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}