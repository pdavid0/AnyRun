package com.wci.android.anyrun.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

//import com.wherecloud.android.K;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * A utility class that encapsulates reading & writing on Shared Preferences.
 * The {@link com.wci.android.anyrun.util.Persistence#save(Object, Context) save(Object, Context)}
 * will save the objet's fields annotated with {@link com.wci.android.anyrun.util.Persist @Persist(String)} annotation.
 * </br></br>
 * Sample usage:
 * </br></br>
 * <code>
 * public class AppState {</br>
 * &nbsp;&nbsp;&nbsp;{@literal @}Persist("my_key") private int myField = 3;</br>
 * }</br>
 * ...
 * </br>
 * persistence = new Persistence("user_settings", new MyPersistenceProvider());</br>
 * persistence.save(myAppState, context);</br>
 * </code>
 * </br>
 * will save 3 at the "my_key" key in the "user_settings" shared preferences.</br>
 * Writing is always done in MODE_PRIVATE. Null values are not saved. When loading,
 * if the key isn't found, the default value is set on the state object's field.</br>
 * Default values are:</br>
 * float = 0.0f</br>
 * int = 0</br>
 * boolean = false</br>
 * long = 0l</br>
 * String = null</br>
 * Object = null</br>
 */
public abstract class Persistence {

    private final String mName;
    private final PersistenceProvider mPersistenceProvider;

    public Persistence(String name, PersistenceProvider persistenceProvider) {
        mName = name;
        mPersistenceProvider = persistenceProvider;

        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        if (persistenceProvider == null) {
            throw new IllegalArgumentException("Persistence provider cannot be null");
        }
    }

    protected String getName() {
        return mName;
    }

    /**
     * Saves the a state (i.e. an object with {@link com.wci.android.anyrun.util.Persist @Persist} annotated fields).
     * into shared preferences.
     *
     * @param state
     * @param context
     */
    public void save(Object state, Context context) {
        SharedPreferences sharedPrefs = context.getSharedPreferences(mName, Context.MODE_PRIVATE);
        Editor editor = sharedPrefs.edit();

        Field[] fields = state.getClass().getFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Persist.class)) {
                String key = field.getAnnotation(Persist.class).key();
                Object value = null;
                try {
                    field.setAccessible(true);
                    value = field.get(state);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    field.setAccessible(false);
                }
                if (value != null) {
                    Type fieldType = field.getType();
                    if (fieldType == Integer.TYPE) {
                        editor.putInt(key, (Integer) value);
                    } else if (fieldType == Boolean.TYPE) {
                        editor.putBoolean(key, (Boolean) value);
                    } else if (fieldType == Float.TYPE) {
                        editor.putFloat(key, (Float) value);
                    } else if (fieldType == Long.TYPE) {
                        editor.putLong(key, (Long) value);
                    } else if (fieldType == String.class) {
                        editor.putString(key, (String) value);
                    } else {
                        String strValue = mPersistenceProvider.toString(value, key);
                        editor.putString(key, strValue);
                    }
                }
            }
        }

        editor.commit();
    }

    /**
     * Loads state variables from shared preferences
     * into a state (i.e. an object with {@link com.wci.android.anyrun.util.Persist @Persist} annotated fields).
     *
     * @param state
     * @param context
     */
    public void load(Object state, Context context) {
        SharedPreferences sharedPrefs = context.getSharedPreferences(mName, Context.MODE_PRIVATE);

        Field[] fields = state.getClass().getFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Persist.class)) {
                String key = field.getAnnotation(Persist.class).key();
                try {
                    field.setAccessible(true);
                    Type fieldType = field.getType();
                    if (fieldType == Integer.TYPE) {
                        int value = sharedPrefs.getInt(key, 0);
                        field.setInt(state, value);
                    } else if (fieldType == Boolean.TYPE) {
                        boolean value = sharedPrefs.getBoolean(key, false);
                        field.setBoolean(state, value);
                    } else if (fieldType == Float.TYPE) {
                        float value = sharedPrefs.getFloat(key, 0.0f);
                        field.setFloat(state, value);
                    } else if (fieldType == Long.TYPE) {
                        long value = sharedPrefs.getLong(key, 0l);
                        field.setLong(state, value);
                    } else if (fieldType == String.class) {
                        String value = sharedPrefs.getString(key, null);
                        field.set(value, key);
                    } else {
                        Object value = mPersistenceProvider.fromString(
                                sharedPrefs.getString(key, null),
                                fieldType,
                                key);
                        field.set(state, value);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    field.setAccessible(false);
                }
            }
        }
    }

//    /**
//     * Logs the content of the shared preferences.
//     *
//     * @param context
//     */
//    public void dump(Context context) {
//        SharedPreferences sharedPrefs = context.getSharedPreferences(mName, Context.MODE_PRIVATE);
//        Map<String, ?> prefMap = sharedPrefs.getAll();
//
//        Log.d(K.TAG, String.format("Dumping shared preferences [%s]", mName));
//        for (String key : prefMap.keySet()) {
//            Log.d(K.TAG, String.format("Key [%s]", key));
//            Log.d(K.TAG, prefMap.get(key) + "");
//        }
//    }

    public interface PersistenceProvider {
        /**
         * Serializes the passed object into a String.
         *
         * @param object - the value for the field annotated with @Persist. May be null.
         * @param key    - the key specified with @Persist annotation, may be used for conditional handling.
         * @return
         */
        String toString(Object object, String key);

        /**
         * Converts the String into an object.
         *
         * @param string - the string from which to deserialize.May be null.
         * @param key    - the key specified with @Persist annotation, may be used for conditional handling.
         * @return
         */
        Object fromString(String string, Type targetType, String key);
    }
}