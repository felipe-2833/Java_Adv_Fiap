package br.com.fiap.cash_up_api.model;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "Email invalido")
    @NotBlank(message = "Email é obrigatório")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "campo obrigatório")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=\\S+$).{6,}$", message = "Senha deve conter pelo menos 6 caracteres, uma letra maiúscula, um número e um caractere especial")
    private String password;

    @NotNull(message = "campo obrigatório")
    @Enumerated(EnumType.STRING)
    private UserRole role;

}
