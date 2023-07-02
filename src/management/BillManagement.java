/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package management;
import java.util.ArrayList;
import java.util.List;
import entity.Bill;
/**
 *
 * @author ADMIN
 */


public class BillManagement {
    private List<Bill> billList;

    public BillManagement() {
        billList = new ArrayList<>();
    }

    public void createBill(Bill bill) {
        billList.add(bill);
    }

    public Bill readBill(int billId) {
        for (Bill bill : billList) {
            if (bill.getId() == billId) {
                return bill;
            }
        }
        return null;
    }

    public void updateBill(int billId, Bill updatedBill) {
        for (Bill bill : billList) {
            if (bill.getId() == billId) {
                bill.setDate(updatedBill.getDate());
                bill.setTotalAmount(updatedBill.getTotalAmount());
                break;
            }
        }
    }

    public void deleteBill(int billId) {
        Bill billToRemove = null;
        for (Bill bill : billList) {
            if (bill.getId() == billId) {
                billToRemove = bill;
                break;
            }
        }
        if (billToRemove != null) {
            billList.remove(billToRemove);
        }
    }
}
