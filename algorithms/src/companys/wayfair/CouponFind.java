package companys.wayfair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CouponFind {

    public static List<Coupon> getCouponForCatagory2(List<Coupon> coupons, String catagory, Map<String, String> ca2caParent){
        List<Coupon>  res = new ArrayList<>();
        while(catagory != null) {
            for(Coupon coupon: coupons) {
                if(coupon.catogry.equals(catagory)){
                    res.add(coupon);
                }
            }
            catagory = ca2caParent.get(catagory);
        }

        return res;
    }

    public static List<Coupon> getCouponForCatagory(List<Coupon> coupons, String catagory, Map<String, String> ca2caParent){
        List<Coupon>  res = new ArrayList<>();
        for(Coupon coupon: coupons) {
            if(isValidCoupon(coupon, catagory, ca2caParent)){
                res.add(coupon);
            }
        }
        return res;
    }

    private static boolean isValidCoupon(Coupon coupon, String catagory, Map<String, String> ca2caParent) {
        String couponCa = coupon.catogry;

        if(couponCa.equals(catagory)) {
            return true;
        }
        while(ca2caParent.containsKey(catagory)) {
            catagory = ca2caParent.get(catagory);
            if(couponCa.equals(catagory)){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // Map<String, String> coupon2ca = new HashMap<>();
        // coupon2ca.put("20% off", "bedding");
        // coupon2ca.put("30% off", "matress");
        // coupon2ca.put("$10 off $100", "kitchen");

        List<Coupon> coupons = new ArrayList<>();
        coupons.add(new Coupon("20% off", "bedding"));
        coupons.add(new Coupon("30% off", "matress"));
        coupons.add(new Coupon("40% off", "sheet"));

        Map<String, String> ca2caParent = new HashMap<>();
        ca2caParent.put("sheet", "bed-accessory");
        ca2caParent.put("matress", "bed-accessory");
        ca2caParent.put("bed-accessory", "bedding");
        ca2caParent.put("Patio", "Garden");

        List<Coupon> res = getCouponForCatagory2(coupons, "matress",ca2caParent);

        for (Coupon coupon : res) {
            System.out.println(coupon.name + ":" + coupon.catogry);
        }
    }
    
}

class Coupon{
    String name;
    String catogry;

    public Coupon(String name, String catogry){
        this.name = name;
        this.catogry = catogry;
    }

}
