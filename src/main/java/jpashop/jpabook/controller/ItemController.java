package jpashop.jpabook.controller;

import jpashop.jpabook.domain.item.Book;
import jpashop.jpabook.domain.item.Item;
import jpashop.jpabook.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/items/new")
    public String save(Model model){
        model.addAttribute("itemForm",new BookForm());
        return "items/createItemForm";
    }
    @PostMapping("/items/new")
    public String createItem(BookForm bookForm){
        Book book = new Book();
        book.setName(bookForm.getName());
        book.setPrice(bookForm.getPrice());
        book.setStockQuantity(bookForm.getStockQuantity());
        book.setAuthor(bookForm.getAuthor());
        book.setIsbn(bookForm.getIsbn());
        itemService.save(book);

        return "redirect:/";
    }

    @RequestMapping("/items")
    public String list(Model model){
        List<Item>items = itemService.findItems();
        model.addAttribute("items",items);
        return "items/itemList";
    }

    @GetMapping("/items/{id}/edit")
    public String updateForm(@PathVariable("id") Long id, Model model ){

        Book item = (Book) itemService.findOne(id);
        BookForm form = new BookForm();
        form.setId(item.getId());
        form.setName(item.getName());
        form.setPrice(item.getPrice());
        form.setStockQuantity(item.getStockQuantity());
        form.setAuthor(item.getAuthor());
        form.setIsbn(item.getIsbn());
        model.addAttribute("form", form);

        return "items/updateItemForm";
    }
    @PostMapping("/items/{id}/edit")
    public String updateItem(@PathVariable("id") Long id, BookForm form){

        itemService.updateItem(form.getId(),form.getName(),form.getPrice(), form.getStockQuantity());

        return "redirect:/items";
    }
}
