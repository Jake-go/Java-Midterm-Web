package ca.sheridancollege.gollej.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Phonecase {

    private Long id;
    private String name;
    private Double price;
    private Integer quantity;
    private String employeeName;

}
