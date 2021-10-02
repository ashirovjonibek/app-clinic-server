package uz.napa.clinic.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.napa.clinic.entity.Permission;
import uz.napa.clinic.entity.Role;
import uz.napa.clinic.entity.User;
import uz.napa.clinic.entity.enums.UserStatus;
import uz.napa.clinic.repository.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Component
public class DataLoader implements CommandLineRunner {
        @Value("${spring.datasource.initialization-mode}")
    private String initialMode;
    final
    UserRepository userRepository;

    final
    PasswordEncoder passwordEncoder;

    final
    RoleRepository roleRepository;

    final
    PermissionRepository permissionRepository;
    final
    RegionRepository regionRepository;
    final
    PositionRepository positionRepository;
    final
    DistrictRepository districtRepository;
    @Autowired
    SectionRepository sectionRepository;
//    final
//    AttachmentTypeRepository attachmentTypeRepository;

    public DataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, PermissionRepository permissionRepository, RegionRepository regionRepository, PositionRepository positionRepository, DistrictRepository districtRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
//        this.attachmentTypeRepository = attachmentTypeRepository;
        this.permissionRepository = permissionRepository;
        this.regionRepository = regionRepository;
        this.positionRepository = positionRepository;
        this.districtRepository = districtRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (initialMode.equals("always")) {
            Permission reader = new Permission(1L, "READ_PERMISSION", "READ PERMISSION");
            Permission writer = new Permission(2L, "WRITE_PERMISSION", "WRITE PERMISSION");
            Permission updater = new Permission(3L, "UPDATE_PERMISSION", "UPDATE PERMISSION");
            Permission deleter = new Permission(4L, "DELETE_PERMISSION", "DELETE PERMISSION");
            List<Permission> permissionEntityList = new ArrayList<>(Arrays.asList(reader, writer, updater, deleter));
            permissionRepository.saveAll(permissionEntityList);

            Role admin = new Role("ADMIN", "ADMIN", "Tizimdagi barcha huquqlarga ega bo'lgan role", true);
            Role user = new Role("USER", "USER", "Tizimdagi cheklangan huquqlarga ega bo'lgan role", true);
            Role moderator = new Role("MODERATOR", "MODERATOR", "Tizimdagi cheklangan  huquqlarga ega bo'lgan role", true);
            Role superModerator = new Role("SUPER_MODERATOR_AND_MODERATOR", "SUPER_MODERATOR_AND_MODERATOR", "Tizimdagi barcha huqularga ega", true);
            Role superModeratorAndModerator = new Role("SUPER_MODERATOR", "SUPER_MODERATOR", "Tizimdagi barcha huqularga ega", true);
            Role listener = new Role("LISTENER", "LISTENER", "Tizimdagi cheklangan  huqularga ega", true);
            admin.setPermissions(Arrays.asList(reader, writer, updater));
            user.setPermissions(Arrays.asList(reader, writer, updater));
            moderator.setPermissions(Arrays.asList(reader, writer, updater, deleter));
            superModerator.setPermissions(Arrays.asList(reader, writer, updater, deleter));
            listener.setPermissions(Arrays.asList(reader, writer, updater, deleter));
            roleRepository.saveAll(Arrays.asList(admin, user, moderator, superModerator, listener,superModeratorAndModerator));
            userRepository.save(
                    new User(
                            "Umid Abdusattorov",
                            new Date(99, 10, 2),
                            positionRepository.getOne(1L),
                            1,
                            districtRepository.getOne(196L),
                            "Toshken sh. Olmazor 2",
                            sectionRepository.getOne(1L),
                            "+998919687077",
                            "islomxujanazarov0501@gmail.com",
                            "erkak",
                            UserStatus.ADMIN,
                            passwordEncoder.encode("1"),
                            roleRepository.findAll()
                    ));


//            attachmentTypeRepository.save(new AttachmentType("image/jpeg"))
//
        }
    }
}

