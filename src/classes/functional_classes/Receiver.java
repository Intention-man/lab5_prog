package classes.functional_classes;

import classes.FormField;
import classes.movies_classes.Movie;
import classes.movies_classes.Movies;
import enums.Country;
import enums.MovieGenre;
import enums.MpaaRating;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class Receiver {

    // initialization

    private static Movies movies;
    static Scanner chosenScanner = new Scanner(System.in);
    static String executedCommand;


    static HashMap<String, String> commandList = new HashMap<>();


    static ArrayList<FormField> form = new ArrayList<>();
    static HashMap<Integer, Object> answers = new HashMap<>();

    static {
        commandList.put("help", "вывести справку по доступным командам");
        commandList.put("info", "вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
        commandList.put("show ", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        commandList.put("add {element}", "добавить новый элемент в коллекцию");
        commandList.put("update id {element}", "обновить значение элемента коллекции, id которого равен заданному");
        commandList.put("remove_by_id id", "удалить элемент из коллекции по его id");
        commandList.put("clear", "очистить коллекцию");
        commandList.put("save", "сохранить коллекцию в файл");
        commandList.put("execute_script file_name", "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
        commandList.put("exit ", "завершить программу (без сохранения в файл)");
        commandList.put("add_if_max {element}", "добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции");
        commandList.put("add_if_min {element}", "добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции");
        commandList.put("history", "вывести последние 12 команд (без их аргументов)");
        commandList.put("remove_any_by_oscars_count oscarsCount", "удалить из коллекции один элемент, значение поля oscarsCount которого эквивалентно заданному");
        commandList.put("sum_of_length", "вывести сумму значений поля length для всех элементов коллекции");
        commandList.put("count_by_oscars_count oscarsCount", "вывести количество элементов, значение поля oscarsCount которых равно заданному");

        form.add(new FormField(0, "String", true, "Введите название фильма"));
        form.add(new FormField(1, "Integer", true, "Введите координату x (это значение должно быть целым и больше -319)"));
        form.add(new FormField(2, "int", true, "Введите координату y"));
        form.add(new FormField(3, "long", true, "Введите количество оскаров у этого фильма"));
        form.add(new FormField(4, "long", true, "Введите длину фильма"));
        form.add(new FormField(5, "MovieGenre", false, "Введите жанр фильма: " + Arrays.asList(MovieGenre.values())));
        form.add(new FormField(6, "MpaaRating", false, "Введите рейтинг фильма:"  + Arrays.asList(MpaaRating.values())));
        form.add(new FormField(7, "String", true, "Введите имя оператора"));
        form.add(new FormField(8, "String", true, "Введите данные паспорта оператора"));
        form.add(new FormField(9, "Country", false, "Введите национальность оператора: "  + Arrays.asList(Country.values())));
        form.add(new FormField(10, "long", false, "Введите местоположение оператора (координата x)"));
        form.add(new FormField(11, "long", false, "Введите местоположение оператора (координата y)"));
        form.add(new FormField(12, "double", false, "Введите местоположение оператора (координата z)"));
    }

    public static void manageClass(Movies movies){
        Receiver.movies = movies;
    }

    // common actions

    public static void suggestNewAction() {
//        System.out.println();
        System.out.println("Введите команду. Чтобы узнать перечень доступных команд ввелите help");
        executedCommand = chosenScanner.nextLine();
    }

    public static void startNewAction(String executedCommand) throws Exception {
        try {
            Handler.addCommandToHistory(executedCommand.split(" ")[0]);
            if (Objects.equals(executedCommand.split(" ")[0], "execute_script")){
                Path path = Paths.get(executedCommand.split(" ")[1]);
                chosenScanner = new Scanner(path);
            }
            switch (executedCommand.split(" ")[0]) {
                case ("help") -> help();
                case ("info") -> info();
                case ("add") -> Handler.addMovie(readInputNewMovieData());
                case ("add_if_max") -> Handler.add_if_max(readInputNewMovieData());
                case ("add_if_min") -> Handler.add_if_min(readInputNewMovieData());
                case ("clear") -> Handler.clear();
                case ("history") -> System.out.println(Handler.getLast12Commands());
                case ("execute_script") -> FileWorker.readFile(chosenScanner);
                case ("save") -> FileWorker.save();
                case ("show") -> show();
                case ("sum_of_length") -> System.out.println(Handler.sum_of_length());
                case ("count_by_oscars_count") -> System.out.println(Handler.countByOscarsCount(Long.parseLong(executedCommand.split(" ")[1])));
                case ("remove_by_id") -> {
                    if (executedCommand.matches("remove_by_id \\d*")) {
                        Handler.removeById(Integer.parseInt(executedCommand.split(" ")[1]));
                    }
                }
                case ("remove_any_by_oscars_count") -> {
                    if (executedCommand.matches("remove_any_by_oscars_count \\d*")) {
                        Handler.removeAnyByOscarsCount(Long.parseLong(executedCommand.split(" ")[1]));
                    }
                }
                case ("update") -> {
                    if (executedCommand.matches("update \\d*")) {
                        Handler.updateMovie(Integer.parseInt(executedCommand.split(" ")[1]), readInputNewMovieData());
                    }
                }
                default -> System.out.println("Введите команду из доступного перечня");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    // commands execution

    public static void help(){
        for (var entry : commandList.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }

    public static void info(){
        System.out.println(movies.getClass());
        System.out.println(movies.getInitializationDate());
        System.out.println(movies.moviesCount());
    }

    public static HashMap<Integer, Object> readInputNewMovieData(){
        int step = 0;
        while(step < Receiver.form.size()){
            try {
                System.out.println(Receiver.form.get(step).getLabel());
                String line = chosenScanner.nextLine();
                if (line.length() == 0 && Receiver.form.get(step).getIsNessersary()) {
                    System.out.println("Значение не может быть пустым");
                    continue;
                } else {
                    if (line.length() == 0){
                        answers.put(step, null);
                        step += 1;
                        continue;
                    }
                }
                switch (Receiver.form.get(step).getExpectedType()){
                    case ("Integer"), ("int") -> {
                       int parsedValue = Integer.parseInt(line);
                        answers.put(step, parsedValue);
                    }
                    case ("long") -> {
                        long parsedValue = Long.parseLong(line);
                        answers.put(step, parsedValue);
                    }
                    case ("double") -> {
                        double parsedValue = Double.parseDouble(line);
                        answers.put(step, parsedValue);
                    }
                    case ("String") -> {
                        answers.put(step, line);
                    }
                    case ("MovieGenre") -> {
                        MovieGenre parsedValue = Enum.valueOf(MovieGenre.class, line);
                        answers.put(step, parsedValue);
                    }
                    case ("MpaaRating") -> {
                        MpaaRating parsedValue = Enum.valueOf(MpaaRating.class, line);
                        answers.put(step, parsedValue);
                    }
                    case ("Country") -> {
                        Country parsedValue = Enum.valueOf(Country.class, line);
                        answers.put(step, parsedValue);
                    }
                }
                step += 1;
            }
            catch (NumberFormatException e) {
                System.out.println("Введите значение правильного типа данных: " + Receiver.form.get(step).getExpectedType());}
            catch (IllegalArgumentException e) {System.out.println("Введите значение из списка допустимых значений ->");}
        }
        return answers;
    }

    public static void show() {
        for (Movie movie : movies.getSortedMovies()) {
            for (String s : movie.getInstance()) {
                System.out.println(s);
            }
        }
    }

    // getters

    public static String getExecutedCommand() {
        return executedCommand;
    }
}