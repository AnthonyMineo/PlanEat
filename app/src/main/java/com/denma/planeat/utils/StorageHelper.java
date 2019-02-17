package com.denma.planeat.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.Toast;

import com.denma.planeat.R;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.List;

public class StorageHelper {

    private static final String FOLDER_NAME = "ShoppingList";

    public static List<String> getShoppingListFromStorage(File rootDestination, Context context, String fileName){
        File file = createOrGetFile(rootDestination, fileName);
        return readOnInternalStorage(context, file);
    }

    public static void setShoppingListInStorage(File rootDestination, Context context, String fileName, List<String> shoppingList){
        File file = createOrGetFile(rootDestination, fileName);
        writeOnInternalStorage(context, shoppingList, file);
    }

    public static File createOrGetFile(File destination, String fileName){
        File folder = new File(destination, FOLDER_NAME);
        return new File(folder, fileName);
    }

    private static List<String> readOnInternalStorage(Context context, File file){
        List<String> result = null;

        if (file.exists()) {
            FileInputStream fis;
            try {
                fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                try {
                    result = (List<String>) ois.readObject();
                } catch (Exception e){
                    e.printStackTrace();
                } finally {
                    ois.close();
                }
            }
            catch (IOException e) {
                Toast.makeText(context, context.getString(R.string.error_reading_shopping_list), Toast.LENGTH_LONG).show();
            }
        }
        return result;
    }

    private static void writeOnInternalStorage(Context context, List<String> shoppingList, File file){
        try {
            file.getParentFile().mkdirs();
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            try {
                oos.writeObject(shoppingList);
            } finally {
                oos.close();
                Toast.makeText(context, context.getString(R.string.saved_list_int_storage), Toast.LENGTH_LONG).show();
            }
        } catch (IOException e) {
            Toast.makeText(context, context.getString(R.string.error_writing_shopping_list), Toast.LENGTH_LONG).show();
        }
    }

}
