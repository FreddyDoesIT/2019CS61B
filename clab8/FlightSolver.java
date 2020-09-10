import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Solver for the Flight problem (#9) from CS 61B Spring 2018 Midterm 2.
 * Assumes valid input, i.e. all Flight start times are >= end times.
 * If a flight starts at the same time as a flight's end time, they are
 * considered to be in the air at the same time.
 */
public class FlightSolver {

    private PriorityQueue<Flight> pq;
    int max;
    public FlightSolver(ArrayList<Flight> flights) {
        if (flights == null || flights.isEmpty()) {
            throw new IllegalArgumentException("Invalid flights information.");
        }
        /* FIX ME */
        pq = new PriorityQueue<>((f1, f2) -> f1.endTime() - f2.endTime());
        pq.offer(flights.get(0));
        max = pq.peek().passengers();
        for (int i = 1; i < flights.size(); i++) {
            Flight flight = flights.get(i);
            if (flight.startTime() > pq.peek().endTime()) {
                max = Math.max(max, getTotalPassengers(pq));
                while (!pq.isEmpty() && pq.peek().endTime < flight.startTime()) {
                    pq.poll();
                }
            }
            pq.offer(flight);
        }
        max = Math.max(max, getTotalPassengers(pq));
    }

    private int getTotalPassengers(PriorityQueue<Flight> pq) {
        int count = 0;
        for (Flight flight: pq) {
            count += flight.passengers();
        }
        return count;
    }

    public int solve() {
        /* FIX ME */
        return max;
    }
}
