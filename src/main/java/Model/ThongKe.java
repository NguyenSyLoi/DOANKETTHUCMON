/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

public class ThongKe {
    private String objectId;     // Mã nhân viên hoặc Mã dự án
    private String objectName;   // Tên nhân viên hoặc Tên dự án
    private int daHoanThanh;     // Số công việc đã hoàn thành
    private int dangLam;         // Số công việc đang làm
    private int quaHan;          // Số công việc quá hạn

    public ThongKe() {
    }

    public ThongKe(String objectId, String objectName, int daHoanThanh, int dangLam, int quaHan) {
        this.objectId = objectId;
        this.objectName = objectName;
        this.daHoanThanh = daHoanThanh;
        this.dangLam = dangLam;
        this.quaHan = quaHan;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public int getDaHoanThanh() {
        return daHoanThanh;
    }

    public void setDaHoanThanh(int daHoanThanh) {
        this.daHoanThanh = daHoanThanh;
    }

    public int getDangLam() {
        return dangLam;
    }

    public void setDangLam(int dangLam) {
        this.dangLam = dangLam;
    }

    public int getQuaHan() {
        return quaHan;
    }

    public void setQuaHan(int quaHan) {
        this.quaHan = quaHan;
    }
}