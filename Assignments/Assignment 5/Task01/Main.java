public class Main {

    public static void main(String[] args) {
        System.out.println("A1: delimited layout passId|registerNo|name|routeNo|boardingStop|validUntil|amountPaid");
        System.out.println("Binary DataOutputStream: UTF, UTF, UTF, int, UTF, UTF, long(amount paise)");
        System.out.println("ADD append; FIND validated sequential scan; LIST_ROUTE filter by route.");
        System.out.println("Recover: quarantine malformed lines, report missing file, use temp file + atomic move for rewrites.");
        System.out.println("RandomAccessFile fixed-width records seek by offset but require padding.");
    }
}
