import java.util.*;

public class ProjPart4 {

    // Candidate class representing a candidate with their name and vote count
    static class Candidate {
        String name;
        int votes;

        public Candidate(String name) {
            this.name = name;
            this.votes = 0;
        }

        public void incrementVotes() {
            this.votes++;
        }

        public int getVotes() {
            return this.votes;
        }

        public String getName() {
            return this.name;
        }
    }

    // Voter class representing a voter with their ranked preferences
    static class Voter {
        String[] preferences;

        public Voter(String[] preferences) {
            this.preferences = preferences;
        }

        public String[] getPreferences() {
            return this.preferences;
        }
    }

    // Method to perform Rank Choice Voting
    static List<String> rankChoiceVoting(List<Candidate> candidates, List<Voter> voters) {
        // Copy the original list of candidates to avoid modification
        List<Candidate> remainingCandidates = new ArrayList<>(candidates);

        // Count the votes for each remaining candidate
        Map<Candidate, Integer> voteCount = new HashMap<>();
        for (Candidate candidate : remainingCandidates) {
            voteCount.put(candidate, 0);
        }

        // Count the votes
        for (Voter voter : voters) {
            for (String preference : voter.getPreferences()) {
                for (Candidate candidate : remainingCandidates) {
                    if (candidate.getName().equals(preference)) {
                        voteCount.put(candidate, voteCount.get(candidate) + 1);
                        break;
                    }
                }
            }
        }

        // Find the candidate(s) with the most votes
        int maxVotes = Collections.max(voteCount.values());
        List<String> winners = new ArrayList<>();
        for (Map.Entry<Candidate, Integer> entry : voteCount.entrySet()) {
            if (entry.getValue() == maxVotes) {
                winners.add(entry.getKey().getName());
            }
        }
        return winners;
    }

    public static void main(String[] args) {
        // Sample candidates
        List<Candidate> candidates = new ArrayList<>();
        candidates.add(new Candidate("Candidate A"));
        candidates.add(new Candidate("Candidate B"));
        candidates.add(new Candidate("Candidate C"));
        candidates.add(new Candidate("Candidate D"));

        // Sample voters and their preferences
        List<Voter> voters = new ArrayList<>();
        voters.add(new Voter(new String[]{"Candidate A", "Candidate B", "Candidate D"}));
        voters.add(new Voter(new String[]{"Candidate C", "Candidate A", "Candidate D"}));
        voters.add(new Voter(new String[]{"Candidate A", "Candidate B", "Candidate D"}));
        voters.add(new Voter(new String[]{"Candidate A", "Candidate D"}));

        // Perform Rank Choice Voting
        List<String> winners = rankChoiceVoting(candidates, voters);

        // Display the winners
        System.out.println("Rank Choice Voting Results:");
        if (winners.size() == 1) {
            System.out.println("The winner is: " + winners.get(0));
        } else if (winners.size() > 1) {
            System.out.println("There is a tie between the following candidates:");
            for (String winner : winners) {
                System.out.println(winner);
            }
        } else {
            System.out.println("No winner");
        }
    }
}
