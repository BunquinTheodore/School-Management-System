import java.util.Date;

public class Payment {
    private int id;
    private final int studentId;
    private double amountPaid;
    private Date paymentDate;
    private String paymentStatus;

    public Payment(int id, int studentId, double amountPaid, Date paymentDate, String paymentStatus) {
        this.id = id;
        this.studentId = studentId;
        this.amountPaid = amountPaid;
        this.paymentDate = paymentDate;
        this.paymentStatus = paymentStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
