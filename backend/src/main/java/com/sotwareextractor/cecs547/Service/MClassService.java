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


@Service
public class MClassService {
    private MClassRepository mClassRepository;
    private MAccessService mAccessService;
    private MPackageService mPackageService;
    private MClassDataMemberService mClassDataMemberService;
    private MClassMethodService mClassMethodService;
    private MInterfaceService mInterfaceService;
    private MConstructorService mConstructorService;

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
        if (existing == null) {
            return null;
        } else {
            return existing.get(0);
        }
    }

    public MClass getOrCreate(DClass dClass) {
        List<MClass> existing = mClassRepository.findByName(dClass.getName());
        if (existing.size() == 0) {
            String className = dClass.getName();
            List<MAccess> mAccess = mAccessService.getOrCreate(dClass.getAccessLevel());
            MPackage mPackage = mPackageService.getOrCreate(dClass.getPackageName());

            // New class instance
            MClass mClass = new MClass(className, mAccess, mPackage);

            mClass.setParent(getOrCreate(dClass.getParentClass()));

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

        else
            return existing.get(0);
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
}
