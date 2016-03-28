package Task;

public class Task {
    private int psd, pcd;
    private int asd, acd;

    public Task() {
        psd = pcd = asd = acd = -1;
    }

    public int getPsd() {
        return psd;
    }

    public void setPsd(int psd) {
        if (psd < 0) {
            throw new IllegalArgumentException("Planned start date cannot be negative");
        }
        if (this.pcd != -1) {
            if (psd > this.pcd) {
                throw new IllegalArgumentException("Planned start date cannot be bigger than planned complete date");
            }
        }
        this.psd = psd;
    }

    public int getPcd() {
        return pcd;
    }

    public void setPcd(int pcd) {
        if (pcd < 0) {
            throw new IllegalArgumentException("Planned complete date cannot be negative");
        }
        if (this.psd != -1) {
            if (pcd < this.psd) {
                throw new IllegalArgumentException("Planned complete date cannot be smaller than planned start date");
            }
        }
        this.pcd = pcd;
    }

    public int getAsd() {
        return asd;
    }

    public void setAsd(int asd) {
        if (asd < 0) {
            throw new IllegalArgumentException("Actual start date cannot be negative");
        }
        if (this.acd != -1) {
            if (asd >= this.acd) {
                throw new IllegalArgumentException("Actual start date cannot be bigger or equal to actual complete date");
            }
        }
        this.asd = asd;
    }

    public int getAcd() {
        return acd;
    }

    public void setAcd(int acd) {
        if (acd < 0) {
            throw new IllegalArgumentException("Actual complete date cannot be negative");
        }
        if (this.asd != -1) {
            if (acd <= this.asd) {
                throw new IllegalArgumentException("Actual complete date cannot be smaller or equal to actual start date");
            }
        } else {
            throw new IllegalStateException("Actual start date is not yet set");
        }
        this.acd = acd;
    }
}