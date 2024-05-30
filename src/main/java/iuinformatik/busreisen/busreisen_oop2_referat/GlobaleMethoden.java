package iuinformatik.busreisen.busreisen_oop2_referat;

public class GlobaleMethoden {

    // Boolean[] to boolean[]
    public static boolean[] convertToPrimitive(Boolean[] booleanObjects) {
        // Check if the input array is null
        if (booleanObjects == null) {
            return null;
        }

        // Create a new boolean[] array with the same length as the input array
        boolean[] booleanPrimitives = new boolean[booleanObjects.length];

        // Iterate through the Boolean[] array
        for (int i = 0; i < booleanObjects.length; i++) {
            // Unbox each Boolean object to its boolean primitive value
            // If the Boolean object is null, you can decide how to handle it
            // Here, we assume null should be converted to false
            booleanPrimitives[i] = booleanObjects[i] != null ? booleanObjects[i] : false;
        }

        return booleanPrimitives;
    }

    // boolean[] to Boolean[]
    public static Boolean[] convertToBoolean(boolean[] booleanPrimitives) {
        // Check if the input array is null
        if (booleanPrimitives == null) {
            return null;
        }

        // Create a new Boolean[] array with the same length as the input array
        Boolean[] booleanObjects = new Boolean[booleanPrimitives.length];

        // Iterate through the boolean[] array
        for (int i = 0; i < booleanPrimitives.length; i++) {
            // Box each boolean primitive value to its Boolean object
            booleanObjects[i] = booleanPrimitives[i];
        }

        return booleanObjects;
    }

    public static void throwError() {
        throw new RuntimeException("Error.");
    }
}
