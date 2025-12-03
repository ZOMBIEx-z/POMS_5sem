// java
package org.lw;

import java.io.*;

public final class FleetStorage {
    private FleetStorage() {}

    public static void save(TaxiFleet fleet, String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(fleet);
        }
    }

    public static TaxiFleet load(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            Object obj = ois.readObject();
            if (obj instanceof TaxiFleet) {
                return (TaxiFleet) obj;
            } else {
                throw new IOException("File does not contain a TaxiFleet object.");
            }
        }
    }
}