package com.modaniru.platform.list;

import java.util.*;

public class SecondExercice {
    static Vacancy[] vacancies = null;
    static class User implements Comparable {
        private String id;
        private String vacancy;
        private int rightlyExercise;
        private int mistakes;

        private User(String string) {
            String[] inf = string.split(",");
            id = inf[0];
            vacancy = inf[1];
            rightlyExercise = Integer.parseInt(inf[2]);
            mistakes = Integer.parseInt(inf[3]);
        }

        @Override
        public String toString() {
            return id;
        }
        public String getVacancy() {
            return vacancy;
        }
        public int getRightlyExercise() {
            return rightlyExercise;
        }
        public int getMistakes() {
            return mistakes;
        }
        @Override
        public int compareTo(Object o) {
            if(rightlyExercise > ((User) o).getRightlyExercise()){
                return 1;
            }
            else if (rightlyExercise == ((User) o).getRightlyExercise()){
                if(mistakes < ((User) o).getMistakes()){
                    return 1;
                }
                else{
                    return -1;
                }
            }
            return -1;
        }
    }
    static class Vacancy{
        private final String vacancyName;
        private final int needPeople;
        private final List<User> candidates;
        private Vacancy(String string){
            String[] inf = string.split(",");
            vacancyName = inf[0];
            needPeople = Integer.parseInt(inf[1]);
            candidates = new ArrayList<>();
        }
        public List<User> getCandidates() {
            return candidates;
        }
        public String getVacancyName() {
            return vacancyName;
        }
        public List<String> getBetterCandidates(){
            sort();
            List<String> users = new ArrayList<>();
            for(int i = 0; i < needPeople; i++){
                users.add(candidates.get(i).id);
            }
            return users;
        }
        private void sort(){
            boolean check = true;
            while(check) {
                check = false;
                for (int j = 1; j < candidates.size(); j++) {
                    if (candidates.get(j-1).compareTo(candidates.get(j)) < 0) {
                        User user = candidates.get(j-1);
                        candidates.set(j-1, candidates.get(j));
                        candidates.set(j, user);
                        check = true;
                    }
                }
            }
        }

        @Override
        public String toString() {
            return "Vacancy{" +
                    "vacancyName='" + vacancyName + '\'' +
                    ", needPeople=" + needPeople +
                    ", candidates=" + candidates +
                    '}';
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int vacancyCount = scanner.nextInt();
        vacancies = new Vacancy[vacancyCount];
        for(int i = 0; i < vacancyCount; i++){
            vacancies[i] = new Vacancy(scanner.next());
        }
        int peopleCount = scanner.nextInt();
        for(int i = 0; i < peopleCount; i++){
            User user = new User(scanner.next());
            Vacancy currentVacancy = findVacancyByName(user.getVacancy());
            currentVacancy.getCandidates().add(user);
        }
        List<String> bestUsersName = new ArrayList<>();
        for(Vacancy v: vacancies){
            bestUsersName.addAll(v.getBetterCandidates());
        }
        Collections.sort(bestUsersName);
        for(String s: bestUsersName){
            System.out.println(s);
        }

    }
    public static Vacancy findVacancyByName(String name){
        for (Vacancy vacancy : vacancies) {
            if (name.equals(vacancy.getVacancyName())) {
                return vacancy;
            }
        }
        return null;
    }
}
