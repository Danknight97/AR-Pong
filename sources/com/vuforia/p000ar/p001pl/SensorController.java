package com.vuforia.p000ar.p001pl;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.HandlerThread;
import java.util.HashMap;
import java.util.Vector;

/* renamed from: com.vuforia.ar.pl.SensorController */
public class SensorController extends HandlerThread implements SensorEventListener {
    private static final int AR_SENSOR_CONFIDENCE_HIGH = 4;
    private static final int AR_SENSOR_CONFIDENCE_LOW = 2;
    private static final int AR_SENSOR_CONFIDENCE_MEDIUM = 3;
    private static final int AR_SENSOR_CONFIDENCE_UNKNOWN = 0;
    private static final int AR_SENSOR_CONFIDENCE_UNRELIABLE = 1;
    private static int AR_SENSOR_INDEX_DONTCARE = -1;
    private static final int AR_SENSOR_PARAMTYPE_ACCURACY = 1879048200;
    private static final int AR_SENSOR_PARAMTYPE_BASE = 1879048192;
    private static final int AR_SENSOR_PARAMTYPE_DATARANGE_MAX = 1879048194;
    private static final int AR_SENSOR_PARAMTYPE_DATARANGE_MIN = 1879048193;
    private static final int AR_SENSOR_PARAMTYPE_RESOLUTION = 1879048196;
    private static final int AR_SENSOR_PARAMTYPE_SENSITIVITY = 1879048208;
    private static final int AR_SENSOR_PARAMTYPE_UPDATEINTERVAL = 1879048224;
    private static final int AR_SENSOR_PARAMTYPE_UPDATEINTERVAL_ABSTRACT = 1879048256;
    private static final int AR_SENSOR_PARAMTYPE_UPDATEINTERVAL_MIN = 1879048320;
    private static final int AR_SENSOR_PARAMTYPE_UPDATERATE_MIN = 1879048448;
    private static final int AR_SENSOR_STATUS_IDLE = 1342242818;
    private static final int AR_SENSOR_STATUS_RUNNING = 1342242819;
    private static final int AR_SENSOR_STATUS_UNINITIALIZED = 1342242817;
    private static final int AR_SENSOR_STATUS_UNKNOWN = 1342242816;
    private static final int AR_SENSOR_TYPE_ACCELEROMETER = 1342177282;
    private static final int AR_SENSOR_TYPE_AMBIENT_LIGHT = 1342177286;
    private static final int AR_SENSOR_TYPE_DEVICE_ROTATION = 1342177288;
    private static final int AR_SENSOR_TYPE_GYROSCOPE = 1342177281;
    private static final int AR_SENSOR_TYPE_MAGNETOMETER = 1342177283;
    private static final int AR_SENSOR_TYPE_PROXIMITY = 1342177285;
    private static final int AR_SENSOR_TYPE_STEP_DETECTOR = 1342177287;
    private static final int AR_SENSOR_TYPE_UNKNOWN = 1342177280;
    private static final int AR_SENSOR_UPDATEINTERVAL_HIGHESTRATE = 4;
    private static final int AR_SENSOR_UPDATEINTERVAL_HIGHRATE = 3;
    private static final int AR_SENSOR_UPDATEINTERVAL_LOWRATE = 1;
    private static final int AR_SENSOR_UPDATEINTERVAL_MEDIUMRATE = 2;
    private static final int AR_SENSOR_UPDATEINTERVAL_UNKNOWN = 0;
    private static boolean CONVERT_FORMAT_TO_ANDROID = false;
    private static boolean CONVERT_FORMAT_TO_PL = true;
    private static final String MODULENAME = "SensorController";
    private static final int SENSORINFO_VALUE_ANDROIDSENSORTYPE = 1;
    private static final int SENSORINFO_VALUE_ISDEFAULT = 2;
    private static final int SENSORINFO_VALUE_PLSENSORTYPE = 0;
    private static final int[] SENSOR_TYPE_CONVERSIONTABLE = {4, AR_SENSOR_TYPE_GYROSCOPE, 1, AR_SENSOR_TYPE_ACCELEROMETER, 2, AR_SENSOR_TYPE_MAGNETOMETER, 8, AR_SENSOR_TYPE_PROXIMITY, 5, AR_SENSOR_TYPE_AMBIENT_LIGHT, 18, AR_SENSOR_TYPE_STEP_DETECTOR, 11, AR_SENSOR_TYPE_DEVICE_ROTATION};
    private static final int _NUM_SENSORINFO_VALUE_ = 3;
    private Vector<SensorCacheInfo> sensorCacheInfo = null;
    private Handler sensorEventHandler;
    private HashMap<Sensor, Integer> sensorIndexMap = null;
    private SensorManager sensorManager;

    /* renamed from: com.vuforia.ar.pl.SensorController$SensorCacheInfo */
    public class SensorCacheInfo {
        int cacheIndex;
        boolean isDefaultSensor;
        int plSensorType;
        int requestedAbstractUpdateRate;
        Sensor sensor;
        float[] valuesForForcedSensorEvent;

        public SensorCacheInfo() {
        }
    }

    private native void newDataAvailable(int i, long j, int i2, float[] fArr);

    public SensorController() {
        super(MODULENAME);
    }

    private SensorCacheInfo getSensorCacheInfo(int sensorCacheInfoIndex) {
        if (sensorCacheInfoIndex < 0 || sensorCacheInfoIndex >= this.sensorCacheInfo.size()) {
            return null;
        }
        return (SensorCacheInfo) this.sensorCacheInfo.get(sensorCacheInfoIndex);
    }

    private int translateSensorType(int fromValue, boolean conversionMode) {
        int i = 0;
        while (i < SENSOR_TYPE_CONVERSIONTABLE.length / 2) {
            if (fromValue != (conversionMode == CONVERT_FORMAT_TO_PL ? SENSOR_TYPE_CONVERSIONTABLE[i * 2] : SENSOR_TYPE_CONVERSIONTABLE[(i * 2) + 1])) {
                i++;
            } else if (conversionMode == CONVERT_FORMAT_TO_PL) {
                return SENSOR_TYPE_CONVERSIONTABLE[(i * 2) + 1];
            } else {
                return SENSOR_TYPE_CONVERSIONTABLE[i * 2];
            }
        }
        if (conversionMode == CONVERT_FORMAT_TO_PL) {
            return AR_SENSOR_TYPE_UNKNOWN;
        }
        return 0;
    }

    private int translateSensorUpdateIntervalToAndroid(int updateIntervalValue_PL) {
        switch (updateIntervalValue_PL) {
            case 1:
                return 3;
            case 2:
                return 2;
            case 3:
                return 1;
            case 4:
                return 0;
            default:
                return -1;
        }
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void onSensorChanged(SensorEvent event) {
        Object intObj = this.sensorIndexMap.get(event.sensor);
        if (intObj != null) {
            SensorCacheInfo sci = getSensorCacheInfo(((Integer) intObj).intValue());
            if (sci != null) {
                int plConfidenceValue = 0;
                switch (event.accuracy) {
                    case 0:
                        plConfidenceValue = 1;
                        break;
                    case 1:
                        plConfidenceValue = 2;
                        break;
                    case 2:
                        plConfidenceValue = 3;
                        break;
                    case 3:
                        plConfidenceValue = 4;
                        break;
                }
                newDataAvailable(sci.cacheIndex, event.timestamp, plConfidenceValue, event.values);
            }
        }
    }

    public boolean init() {
        this.sensorManager = null;
        this.sensorCacheInfo = new Vector<>();
        this.sensorIndexMap = new HashMap<>();
        return true;
    }

    public int getAllSupportedSensors() {
        Activity activity = SystemTools.getActivityFromNative();
        if (activity == null) {
            SystemTools.logSystemError("No valid activity set in native!");
            return -1;
        }
        Context context = activity.getApplication();
        if (context == null) {
            return -1;
        }
        this.sensorManager = (SensorManager) context.getSystemService("sensor");
        if (this.sensorManager == null) {
            SystemTools.setSystemErrorCode(6);
            SystemTools.logSystemError("Failed to retrieve Context's Sensor Service");
            return -1;
        } else if (this.sensorCacheInfo.size() > 0) {
            return this.sensorCacheInfo.size();
        } else {
            for (Sensor sensor : this.sensorManager.getSensorList(-1)) {
                int sensorType = sensor.getType();
                boolean isDefaultSensor = sensor.equals(this.sensorManager.getDefaultSensor(sensorType));
                int plSensorType = translateSensorType(sensorType, CONVERT_FORMAT_TO_PL);
                if (plSensorType != AR_SENSOR_TYPE_UNKNOWN) {
                    SensorCacheInfo sci = new SensorCacheInfo();
                    sci.sensor = sensor;
                    sci.plSensorType = plSensorType;
                    sci.isDefaultSensor = isDefaultSensor;
                    sci.cacheIndex = this.sensorCacheInfo.size();
                    sci.requestedAbstractUpdateRate = 0;
                    this.sensorCacheInfo.add(sci);
                    this.sensorIndexMap.put(sci.sensor, Integer.valueOf(sci.cacheIndex));
                }
            }
            return this.sensorCacheInfo.size();
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean open(int sensorType, int index) {
        SensorCacheInfo sci = null;
        if (index == AR_SENSOR_INDEX_DONTCARE) {
            int i = 0;
            while (true) {
                if (i >= this.sensorCacheInfo.size()) {
                    break;
                }
                SensorCacheInfo tmpSCI = (SensorCacheInfo) this.sensorCacheInfo.get(i);
                if (tmpSCI.plSensorType == sensorType && tmpSCI.isDefaultSensor) {
                    sci = tmpSCI;
                    break;
                }
                i++;
            }
        } else {
            sci = (SensorCacheInfo) this.sensorCacheInfo.get(index);
        }
        if (sci == null) {
            SystemTools.setSystemErrorCode(2);
            SystemTools.logSystemError("No sensor matching the requested sensor device info has been found");
            return false;
        }
        if (this.sensorEventHandler == null) {
            try {
                start();
                this.sensorEventHandler = new Handler(getLooper());
            } catch (Exception e) {
                SystemTools.setSystemErrorCode(6);
                SystemTools.logSystemError("Failed to " + (isAlive() ? "retrieve a handler for the sensor event handler thread" : "start Java handler thread for sensor events") + ": " + e.toString());
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: 0000 */
    public boolean close(int sensorCacheInfoIndex) {
        SensorCacheInfo sci = getSensorCacheInfo(sensorCacheInfoIndex);
        if (sci == null) {
            SystemTools.setSystemErrorCode(4);
            SystemTools.logSystemError("Sensor handle is invalid");
            return false;
        }
        boolean result = false;
        try {
            this.sensorManager.unregisterListener(this, sci.sensor);
            result = true;
        } catch (Exception e) {
            SystemTools.setSystemErrorCode(6);
            SystemTools.logSystemError("Failed to unregister sensor event listerer");
        }
        System.gc();
        return result;
    }

    /* access modifiers changed from: 0000 */
    public int[] getSensorInfoValues(int sensorCacheInfoIndex) {
        int i = 1;
        SensorCacheInfo sci = getSensorCacheInfo(sensorCacheInfoIndex);
        if (sci == null) {
            return null;
        }
        int[] sensorInfoValues = new int[3];
        sensorInfoValues[0] = sci.plSensorType;
        sensorInfoValues[1] = sci.sensor.getType();
        if (!sci.isDefaultSensor) {
            i = 0;
        }
        sensorInfoValues[2] = i;
        return sensorInfoValues;
    }

    /* access modifiers changed from: 0000 */
    public String getSensorName(int sensorCacheInfoIndex) {
        SensorCacheInfo sci = getSensorCacheInfo(sensorCacheInfoIndex);
        if (sci == null) {
            return null;
        }
        return sci.sensor.getName();
    }

    /* access modifiers changed from: 0000 */
    public Object getTypedSensorParameter(int sensorCacheInfoIndex, int type) {
        SensorCacheInfo sci = getSensorCacheInfo(sensorCacheInfoIndex);
        if (sci == null) {
            SystemTools.setSystemErrorCode(4);
            SystemTools.logSystemError("Sensor handle is invalid");
            return null;
        }
        switch (type) {
            case AR_SENSOR_PARAMTYPE_DATARANGE_MIN /*1879048193*/:
            case AR_SENSOR_PARAMTYPE_ACCURACY /*1879048200*/:
            case AR_SENSOR_PARAMTYPE_SENSITIVITY /*1879048208*/:
            case AR_SENSOR_PARAMTYPE_UPDATEINTERVAL /*1879048224*/:
                SystemTools.setSystemErrorCode(3);
                SystemTools.logSystemError("Querying sensor parameter " + type + " is not supported for sensor type " + sci.plSensorType + (type == AR_SENSOR_PARAMTYPE_UPDATEINTERVAL ? " when using the Java-based sensor API" : BuildConfig.FLAVOR));
                return null;
            case AR_SENSOR_PARAMTYPE_DATARANGE_MAX /*1879048194*/:
                return Float.valueOf(sci.sensor.getMaximumRange());
            case AR_SENSOR_PARAMTYPE_RESOLUTION /*1879048196*/:
                return Float.valueOf(sci.sensor.getResolution());
            case AR_SENSOR_PARAMTYPE_UPDATEINTERVAL_ABSTRACT /*1879048256*/:
                return Integer.valueOf(sci.requestedAbstractUpdateRate);
            case AR_SENSOR_PARAMTYPE_UPDATEINTERVAL_MIN /*1879048320*/:
                if (SystemTools.checkMinimumApiLevel(9)) {
                    return Integer.valueOf(sci.sensor.getMinDelay());
                }
                SystemTools.setSystemErrorCode(3);
                SystemTools.logSystemError("Unknown sensor parameter");
                return null;
            default:
                try {
                    SystemTools.setSystemErrorCode(3);
                    SystemTools.logSystemError("Unknown sensor parameter");
                    return null;
                } catch (Exception e) {
                    SystemTools.setSystemErrorCode(6);
                    SystemTools.logSystemError("Failed to get sensor parameter: " + e.toString());
                    return null;
                }
        }
        SystemTools.setSystemErrorCode(6);
        SystemTools.logSystemError("Failed to get sensor parameter: " + e.toString());
        return null;
    }

    /* access modifiers changed from: 0000 */
    public boolean setTypedSensorParameter(int sensorCacheInfoIndex, int type, Object value) {
        SensorCacheInfo sci = getSensorCacheInfo(sensorCacheInfoIndex);
        if (sci == null) {
            SystemTools.setSystemErrorCode(4);
            SystemTools.logSystemError("Sensor handle is invalid");
            return false;
        }
        switch (type) {
            case AR_SENSOR_PARAMTYPE_DATARANGE_MIN /*1879048193*/:
            case AR_SENSOR_PARAMTYPE_DATARANGE_MAX /*1879048194*/:
            case AR_SENSOR_PARAMTYPE_RESOLUTION /*1879048196*/:
            case AR_SENSOR_PARAMTYPE_ACCURACY /*1879048200*/:
            case AR_SENSOR_PARAMTYPE_SENSITIVITY /*1879048208*/:
            case AR_SENSOR_PARAMTYPE_UPDATEINTERVAL /*1879048224*/:
            case AR_SENSOR_PARAMTYPE_UPDATEINTERVAL_MIN /*1879048320*/:
                SystemTools.setSystemErrorCode(3);
                SystemTools.logSystemError("Sensor parameter " + type + " cannot be set for sensor type " + sci.plSensorType + (type == AR_SENSOR_PARAMTYPE_UPDATEINTERVAL ? " when using the Java-based sensor API" : BuildConfig.FLAVOR));
                return false;
            case AR_SENSOR_PARAMTYPE_UPDATEINTERVAL_ABSTRACT /*1879048256*/:
                int updateIntervalValue = ((Number) value).intValue();
                if (updateIntervalValue < 1 || updateIntervalValue > 4) {
                    SystemTools.setSystemErrorCode(2);
                    SystemTools.logSystemError("Invalid abstract sensor update interval (" + updateIntervalValue + ")");
                    return false;
                }
                sci.requestedAbstractUpdateRate = updateIntervalValue;
                return true;
            default:
                try {
                    SystemTools.setSystemErrorCode(3);
                    SystemTools.logSystemError("Unknown sensor parameter");
                    return false;
                } catch (Exception e) {
                    SystemTools.setSystemErrorCode(6);
                    SystemTools.logSystemError("Failed to get sensor parameter: " + e.toString());
                    return false;
                }
        }
        SystemTools.setSystemErrorCode(6);
        SystemTools.logSystemError("Failed to get sensor parameter: " + e.toString());
        return false;
    }

    /* access modifiers changed from: 0000 */
    public boolean start(int sensorCacheInfoIndex) {
        int requestedUpdateRateAndroid;
        SensorCacheInfo sci = getSensorCacheInfo(sensorCacheInfoIndex);
        if (sci == null) {
            SystemTools.setSystemErrorCode(4);
            SystemTools.logSystemError("Sensor handle is invalid");
            return false;
        }
        int updateRateAndroid = translateSensorUpdateIntervalToAndroid(sci.requestedAbstractUpdateRate);
        if (updateRateAndroid < 0) {
            requestedUpdateRateAndroid = 1;
        } else {
            requestedUpdateRateAndroid = updateRateAndroid;
        }
        boolean result = false;
        try {
            result = this.sensorManager.registerListener(this, sci.sensor, requestedUpdateRateAndroid, this.sensorEventHandler);
        } catch (Exception e) {
        }
        if (result) {
            return result;
        }
        SystemTools.setSystemErrorCode(6);
        SystemTools.logSystemError("Failed to start sensor, could not register sensor event listerer");
        return result;
    }

    /* access modifiers changed from: 0000 */
    public boolean stop(int sensorCacheInfoIndex) {
        SensorCacheInfo sci = getSensorCacheInfo(sensorCacheInfoIndex);
        if (sci == null) {
            SystemTools.setSystemErrorCode(4);
            SystemTools.logSystemError("Sensor handle is invalid");
            return false;
        }
        try {
            this.sensorManager.unregisterListener(this, sci.sensor);
            return true;
        } catch (Exception e) {
            SystemTools.setSystemErrorCode(6);
            SystemTools.logSystemError("Failed to stop sensor, could not unregister sensor event listerer");
            return false;
        }
    }
}
