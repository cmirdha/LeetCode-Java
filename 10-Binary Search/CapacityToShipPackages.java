class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int low =0;
        int high =0;
        int result =0;
        for(int weight :weights){
            low = Math.max(low, weight);
            high +=weight;
        }

        while(low<=high){
            int mid = (low+high)/2;
            if(ifShippingPossible( weights, days, mid)){
                result = mid;
                high = mid-1;
            }
            else
                low = mid+1;
        }

        return result;

    }


    public boolean ifShippingPossible(int[] weights, int days, int ship) {

        int daysUsed = 1;
        int currentLoad = 0;

        for (int weight : weights) {

            if (currentLoad + weight > ship) {
                daysUsed++;
                currentLoad = weight;
            } else {
                currentLoad += weight;
            }
        }

        return daysUsed <= days;
    }


}