package jpashop.jpabook.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter @Setter
public class MemberForm {

    @NotBlank(message = "Name may not be empty.")
    String name;
    String city;
    String street;
    String zipcode;
}
