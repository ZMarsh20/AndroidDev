package com.example.colordatabase;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.AsyncTask;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Random;

public class ColorAdapter extends RecyclerView.Adapter {
    int colorNum = 0;
    Context context;
    static Cursor cursor;
    TextView textView;
    ProgressBar progressBar;
    String sortMethod = null;
    static SQLiteDatabase sqLiteDatabase;
    ColorDatabaseHelper colorDatabaseHelper;

    public static final String TABLE_ID = ColorDatabaseHelper.TABLE_ID;
    public static final String TABLE = ColorDatabaseHelper.TABLE;
    public static final String RED = ColorDatabaseHelper.RED;
    public static final String GREEN = ColorDatabaseHelper.GREEN;
    public static final String BLUE = ColorDatabaseHelper.BLUE;
    public static final String FAVORITES = ColorDatabaseHelper.FAVORITES;

    private Listener listener;

    public interface Listener{
        void onClick(int position);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        public MyViewHolder(CardView cardView) {
            super(cardView);
            this.cardView = cardView;
        }

        public CardView getCardView() {
            return cardView;
        }
    }

    public ColorAdapter(Context context) {
        colorDatabaseHelper = new ColorDatabaseHelper(context);
        sqLiteDatabase = colorDatabaseHelper.getReadableDatabase();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.color_card_view, parent,false);
        CardView cardView = view.findViewById(R.id.cardView);
        context = parent.getContext();
        return new MyViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        CardView cardView = myViewHolder.getCardView();

        cursor = sqLiteDatabase.query(TABLE, new String[]{TABLE_ID, RED, GREEN, BLUE, FAVORITES},
                null, null, null, null, sortMethod, null);
        cursor.moveToPosition(position);

        TextView textView = cardView.findViewById(R.id.textViewColorRGB);
        FrameLayout frameLayout = cardView.findViewById(R.id.frameLayoutColor);
        CheckBox checkBox = cardView.findViewById(R.id.checkBoxFav);
        int red = cursor.getInt(cursor.getColumnIndex(RED));
        int green = cursor.getInt(cursor.getColumnIndex(GREEN));
        int blue = cursor.getInt(cursor.getColumnIndex(BLUE));

        textView.setText(red + ", " + green + ", " + blue);
        frameLayout.setBackgroundColor(Color.rgb(red,green,blue));
        checkBox.setChecked(cursor.getInt(cursor.getColumnIndex(FAVORITES)) != 0);

        cardView.findViewById(R.id.checkBoxFav).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return colorDatabaseHelper.getCount();
    }

    private class MyAsyncTask extends AsyncTask<Integer, Double, String>{
        private double d;
        private int hundreth;

        @Override
        protected void onProgressUpdate(Double... values) {
            progressBar.setProgress((int) Math.round(d));
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            d = 0;
            if (colorNum > progressBar.getMax()) {
                hundreth = colorNum / progressBar.getMax();
            } else if (colorNum > (progressBar.getMax() / 10)) {
                hundreth = colorNum / (progressBar.getMax() / 10);
            } else {
                hundreth = 1;
            }
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            progressBar.setVisibility(View.GONE);
            int count = colorDatabaseHelper.getCount();
            textView.setText(Integer.toString(count));
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(Integer... integers) {
            Random r = new Random();
            for (int i = 0; i < colorNum; i++){
                ContentValues contentValues = new ContentValues();
                int red = r.nextInt(256);
                int green = r.nextInt(256);
                int blue = r.nextInt(256);
                contentValues.put(RED, red);
                contentValues.put(GREEN, green);
                contentValues.put(BLUE, blue);
                contentValues.put(FAVORITES, false);
                sqLiteDatabase.insert(TABLE, null, contentValues);
                if (i % hundreth == 0) {
                    d++;
                    publishProgress(d);
                }
            }
            return null;
        }
    }

    public void addColors(){
        MyAsyncTask myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute();
    }

    public void sort(int num) {
        switch (num) {
            case 0:
                sortMethod = RED + " DESC";
                break;
            case 1:
                sortMethod = GREEN + " DESC";
                break;
            case 2:
                sortMethod = BLUE + " DESC";
                break;
            case 3:
                sortMethod = FAVORITES + " DESC";
                break;
            default:
                sortMethod = null;
        }
        this.notifyDataSetChanged();
    }

    public void dialogAdd(MainActivity mainActivity, ProgressBar p, TextView t) {
        progressBar = p;
        textView = t;
        AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
        builder.setTitle("Add Colors");

        final EditText input = new EditText(mainActivity);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);

        builder.setView(input);

        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                colorNum = Integer.valueOf(input.getText().toString());
                addColors();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();

    }

    public void dialogSort(MainActivity mainActivity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
        builder.setTitle("Sort How?");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(mainActivity, android.R.layout.select_dialog_singlechoice);
        arrayAdapter.add("RED");
        arrayAdapter.add("GREEN");
        arrayAdapter.add("BLUE");
        arrayAdapter.add("FAVORITES");
        arrayAdapter.add("DEFAULT");

        builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sort(which);
                Log.d("HERE", String.valueOf(which));
            }
        });

        builder.show();
    }

    public static void fav(int position) {
        cursor.moveToPosition(position);
        ContentValues contentValues = new ContentValues();
        contentValues.put(FAVORITES, (cursor.getInt(cursor.getColumnIndex(FAVORITES)) == 0));
        sqLiteDatabase.update(TABLE, contentValues, TABLE_ID + "= ?",
                new String[] {cursor.getString(cursor.getColumnIndex(TABLE_ID))});
        cursor = sqLiteDatabase.query(TABLE, new String[] {TABLE_ID,RED,GREEN,BLUE,FAVORITES},
                null, null, null, null, null, null);
    }
}
