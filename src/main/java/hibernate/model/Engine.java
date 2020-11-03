package hibernate.model;


import javax.persistence.*;
import lombok.*;


@Data                               // анотация выполяет роль замены гетеров и сетерв
@ToString
@EqualsAndHashCode
@NoArgsConstructor                  // анотация заменяет обязательное присутствие пустого конструктора
@AllArgsConstructor                 // анотация заменяет присутствие конструктора c параметрами
public class Engine {
    /**
     * если включены анотации по конструкторам, то нельзя делать ни какой другой конструктор вручную
     * название полей как и у базы  */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String model;
    private int power;
}