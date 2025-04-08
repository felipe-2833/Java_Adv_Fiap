import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.fiap.cash_up_api.model.Category;
import br.com.fiap.cash_up_api.model.TransactionType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    
    @Id 
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "campo obrigatorio")
    @Size(min = 5, max = 255, message="deve ter pelo menos 5 caracteres")
    private String description;

    @Positive(message = "deve ser maior que zero")
    private BigDecimal amount;

    @PastOrPresent(message = "data invalida, não pode ser no futuro")
    private LocalDate date;

    @NotNull(message = "campo obrigatorio")
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @NotNull(message = "campo obrigatorio")
    // M : 1
    @ManyToOne
    private Category category;
}
