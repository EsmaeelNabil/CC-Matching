package com.example.esmaeel.matchingclothes;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.mukesh.image_processing.ImageProcessor;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    Button pressbtn;
    RelativeLayout bg;
    ImageView pant, tshirt;
    CircleImageView color1, color2, color3, colorc;
    ImageButton topleft, bottomleft, topright, bottomright;
    SeekBar seekBar;
    ImageProcessor imageProcessor = new ImageProcessor();
    int lastColor = 0;
    String bottom = "pant";
    int progress2=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pressbtn = (Button) findViewById(R.id.pressbtn);
        bg = (RelativeLayout) findViewById(R.id.bg);
        tshirt = (ImageView) findViewById(R.id.tshirtImg);
        pant = (ImageView) findViewById(R.id.pantImg);
        topleft = (ImageButton) findViewById(R.id.topleft);
        topright = (ImageButton) findViewById(R.id.topright);
        bottomleft = (ImageButton) findViewById(R.id.bottomleft);
        bottomright = (ImageButton) findViewById(R.id.bottomright);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        color1 = (CircleImageView) findViewById(R.id.color1);
        color2 = (CircleImageView) findViewById(R.id.color2);
        color3 = (CircleImageView) findViewById(R.id.color3);
        colorc = (CircleImageView) findViewById(R.id.colorc);

        //-----------------

        color1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bg.setBackgroundColor(Color.RED);
            }
        });
        color2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bg.setBackgroundColor(Color.YELLOW);
            }
        });
        color3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bg.setBackgroundColor(Color.GREEN);
            }
        });
        colorc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorPickerDialogBuilder
                        .with(MainActivity.this)
                        .setTitle("Choose color")
                        .initialColor(Color.CYAN)
                        .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                        .showColorPreview(true)
                        .density(12)
                        .setOnColorSelectedListener(new OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int selectedColor) {
                                bg.setBackgroundColor(selectedColor);
                            }
                        })
                        .setPositiveButton("ok", new ColorPickerClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                                bg.setBackgroundColor(selectedColor);
                            }
                        })
                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })

                        .build()
                        .show();
            }
        });

        topright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        topleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        bottomright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bottom.equals("pant")) {
                    Bitmap bitmap = BitmapFactory.decodeResource(MainActivity.this.getResources(), R.drawable.shorttt);
                    pant.setImageBitmap(bitmap);
                    bottom = "short";
                } else if (bottom.equals("short")) {
                    Bitmap bitmap = BitmapFactory.decodeResource(MainActivity.this.getResources(), R.drawable.pan);
                    pant.setImageBitmap(bitmap);
                    bottom = "pant";
                }
            }
        });
        bottomleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bottom.equals("pant")) {
                    Bitmap bitmap = BitmapFactory.decodeResource(MainActivity.this.getResources(), R.drawable.shorttt);
                    pant.setImageBitmap(bitmap);
                    bottom = "short";
                } else if (bottom.equals("short")) {
                    Bitmap bitmap = BitmapFactory.decodeResource(MainActivity.this.getResources(), R.drawable.pan);
                    pant.setImageBitmap(bitmap);

                    bottom = "pant";
                }
            }
        });
        //------------------

        tshirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeClothesColor(tshirt);
            }
        });

        pant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeClothesColor(pant);
            }
        });

        pressbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Bitmap bitmap = BitmapFactory.decodeResource(MainActivity.this.getResources(),R.drawable.tshirt);
//                Bitmap sourceBitmap = ((BitmapDrawable)tshirt.getDrawable()).getBitmap();
                Drawable drawable = tshirt.getDrawable();
                ColorFilter colorFilter = ColorFilterGenerator.from(drawable).to(lastColor);
                tshirt.setColorFilter(colorFilter);
//                tshirt.setColorFilter(ColorFilterGenerator.adjustHue(200)); // 162 degree rotation

            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                progress2 = progress;


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Bitmap bitmap = BitmapFactory.decodeResource(MainActivity.this.getResources(), R.drawable.poloo);
                ImageProcessor processor = new ImageProcessor();
                Bitmap newBitmap = processor.applySaturationFilter(bitmap,progress2);
                tshirt.setImageBitmap(newBitmap);
            }
        });

    }

    //--------------------
    public Bitmap replaceColor(Bitmap mImage, int fromColor, int targetColor) {
        if (mImage == null) {
            return null;
        }

        int width = mImage.getWidth();
        int height = mImage.getHeight();
        int[] pixels = new int[width * height];
        mImage.getPixels(pixels, 0, width, 0, 0, width, height);

        for (int x = 0; x < pixels.length; ++x) {
            pixels[x] = (pixels[x] == fromColor) ? targetColor : pixels[x];
        }

        Bitmap newImage = Bitmap.createBitmap(width, height, mImage.getConfig());
        newImage.setPixels(pixels, 0, width, 0, 0, width, height);

        return newImage;
    }
    //--------------------

    public void ChangeClothesColor(final ImageView img) {
        if (lastColor == 0) {
            ColorPickerDialogBuilder
                    .with(MainActivity.this)
                    .setTitle("Choose color")
                    .initialColor(Color.CYAN)
                    .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                    .showColorPreview(true)
                    .density(12)
                    .setOnColorSelectedListener(new OnColorSelectedListener() {
                        @Override
                        public void onColorSelected(int selectedColor) {

                        }
                    })
                    .setPositiveButton("ok", new ColorPickerClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {


                            Drawable drawable = img.getDrawable();
                            ColorFilter colorFilter = ColorFilterGenerator.from(drawable).to(selectedColor);
                            img.setColorFilter(colorFilter);
                            lastColor = selectedColor;

//                        Bitmap bitmap = ((BitmapDrawable)img.getDrawable()).getBitmap();
//                        ImageProcessor processor = new ImageProcessor();
//                        Bitmap newBitmap = processor.applyBlackFilter(bitmap);
//                        img.setImageBitmap(newBitmap);

//                            Bitmap bitmap = ((BitmapDrawable)img.getDrawable()).getBitmap();
//                            Bitmap newBitmap =  changeImageColor(bitmap,selectedColor);
//                            img.setImageBitmap(newBitmap);

                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })

                    .build()
                    .show();
        } else {
            ColorPickerDialogBuilder
                    .with(MainActivity.this)
                    .setTitle("Choose color")
                    .initialColor(lastColor)
                    .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                    .showColorPreview(true)
                    .density(12)
                    .setOnColorSelectedListener(new OnColorSelectedListener() {
                        @Override
                        public void onColorSelected(int selectedColor) {

                        }
                    })
                    .setPositiveButton("ok", new ColorPickerClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {


                            Drawable drawable = img.getDrawable();
                            ColorFilter colorFilter = ColorFilterGenerator.from(drawable).to(selectedColor);
                            img.setColorFilter(colorFilter);
                            lastColor = selectedColor;

//                            Bitmap bitmap = ((BitmapDrawable)img.getDrawable()).getBitmap();
//                            ImageProcessor processor = new ImageProcessor();
//                            Bitmap newBitmap = processor.applyBlackFilter(bitmap);
//                            img.setImageBitmap(newBitmap);


//                            Bitmap bitmap = ((BitmapDrawable)img.getDrawable()).getBitmap();
//                            Bitmap newBitmap =  changeImageColor(bitmap,selectedColor);
//                            img.setImageBitmap(newBitmap);


                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })

                    .build()
                    .show();
        }

    }

    public static Bitmap changeImageColor(Bitmap srcBmp, int dstColor) {

        int width = srcBmp.getWidth();
        int height = srcBmp.getHeight();

        float srcHSV[] = new float[3];
        float dstHSV[] = new float[3];
        float myHSV[] = new float[3];

        Color.colorToHSV(Color.rgb(2, 195, 255), myHSV);

        Bitmap dstBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {

                int pixel = srcBmp.getPixel(col, row);
                Color.RGBToHSV(Color.red(pixel), Color.green(pixel), Color.blue(pixel), srcHSV);

                if (srcHSV == myHSV) {
                    Color.colorToHSV(dstColor, dstHSV);
                    dstBitmap.setPixel(col, row, Color.HSVToColor(dstHSV));
                } else {
                    dstBitmap.setPixel(col, row, Color.HSVToColor(srcHSV));
                }

//                Color.colorToHSV(srcBmp.getPixel(col, row), srcHSV);
//                Color.colorToHSV(dstColor, dstHSV);
                // If it area to be painted set only value of original image
//                dstHSV[2] = srcHSV[2];  // value
//                dstBitmap.setPixel(col, row, Color.HSVToColor(dstHSV));
            }
        }

        return dstBitmap;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

}
