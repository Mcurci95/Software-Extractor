package com.sotwareextractor.cecs547.Service;

import com.sotwareextractor.cecs547.POJO.DClass;
        import com.sotwareextractor.cecs547.POJO.DClassField;
        import com.sotwareextractor.cecs547.POJO.DClassMethod;
        import com.sotwareextractor.cecs547.Model.*;
        import com.sotwareextractor.cecs547.Repository.MClassRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.util.ArrayList;
        import java.util.List;
import java.util.Objects;


@Service
public class MClassService {
    private final MClassRepository mClassRepository;
    private final MAccessService mAccessService;
    private final MPackageService mPackageService;
    private final MClassDataMemberService mClassDataMemberService;
    private final MClassMethodService mClassMethodService;
    private final MInterfaceService mInterfaceService;
    private final MConstructorService mConstructorService;

    @Autowired
    public MClassService(MClassRepository mClassRepository, MAccessService mAccessService,
                         MPackageService mPackageService, MClassDataMemberService mClassDataMemberService,
                         MClassMethodService mClassMethodService, MInterfaceService mInterfaceService,
                         MConstructorService mConstructorService) {
        this.mClassRepository = mClassRepository;
        this.mAccessService = mAccessService;
        this.mPackageService = mPackageService;
        this.mClassDataMemberService = mClassDataMemberService;
        this.mClassMethodService = mClassMethodService;
        this.mInterfaceService = mInterfaceService;
        this.mConstructorService = mConstructorService;
    }

    public MClass getOrCreate(String parentClass) {
        if (parentClass == null) return null;
        var existing = mClassRepository.findByName(parentClass);
        if (existing == null || existing.size() == 0) {
            return null;
        } else {
            return existing.get(0);
        }
    }

    public MClass getOrCreate(DClass dClass) {
        List<MClass> existing = mClassRepository.findByName(dClass.getName());
        Integer version = null;

        // Check existing, return the instance if instance has the same package name
        for (MClass instance : existing) {
            if (instance.getName().equals(dClass.getName())) {
                if (instance.getmPackage() != null) {
                    if (instance.getmPackage().getName().equals(dClass.getPackageName())) {
                        version = instance.getVersion();
                    }
                }
                else {
                    version = instance.getVersion();
                }
            }
        }

        // If not, create a new record
        String className = dClass.getName();
        List<MAccess> mAccess = mAccessService.getOrCreate(dClass.getAccessLevel());
        MPackage mPackage = mPackageService.getOrCreate(dClass.getPackageName());
        MClass mClass = new MClass(className, mAccess, mPackage);
        MClass parent = getOrCreate(dClass.getParentClass());
        if (parent != null) {
            mClass.setParent(parent.getName());
        }
        mClass.setVersion(Objects.requireNonNullElse(version, 0) + 1);

        // data member
        List<MClassDataMember> dataMembersEntities = storeClassDataMember(dClass.getFields(), mClass);
        mClass.setmClassDataMembers(dataMembersEntities);

        List<MMethod> methodEntities = storeClassMethod(dClass.getdClassMethods(), mClass);
        mClass.setmMethods(methodEntities);

        List<MInterface> interfaceEntities = mInterfaceService.getOrCreate(dClass.getImplementInterfaces());
        mClass.setImplementInterfaces(interfaceEntities);

        List<MConstructor> constructors = mConstructorService.getOrCreate(dClass.getConstructors(), mClass);
        mClass.setmConstructors(constructors);

        return mClassRepository.save(mClass);
    }

    public MClass findByName(String name) {
        List<MClass> existing = mClassRepository.findByName(name);
        if (existing.size() == 0)
            return null;
        else
            return existing.get(0);
    }

    private List<MClassDataMember> storeClassDataMember(List<DClassField> fields, MClass mClass) {
        List<MClassDataMember> mClassDataMembers = new ArrayList<>();
        for (DClassField field : fields) {
            MClassDataMember mClassDataMember = mClassDataMemberService.getOrCreate(field, mClass);
            mClassDataMembers.add(mClassDataMember);
        }
        return mClassDataMembers;
    }

    private List<MMethod> storeClassMethod(List<DClassMethod> methods, MClass mClass) {
        List<MMethod> mMethods = new ArrayList<>();
        for (DClassMethod method : methods) {
            MMethod methodEntity = mClassMethodService.getOrCreate(method, mClass);
            if (methodEntity != null) mMethods.add(methodEntity);
        }
        return mMethods;
    }

    public List<MClass> findAll() {
        return this.mClassRepository.findAll();
    }

    public List<String> distinctClassNames() {
        return this.mClassRepository.findDistinctClassNames();
    }

    public List<MClass> parentNames(String parent) {
        return this.mClassRepository.findNameByParent(parent);
    }
}
