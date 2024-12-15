package src.main.collections_and_arrays;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


//프레디케이트와 일치하는 컬렉션 내 원소 삭제
public class RemoveByPredicate {

    public List<Long> v1(Dto dto) {
        List<Long> copyList = new ArrayList<>(dto.inputList);

        Iterator<Long> iterator = copyList.iterator();
        while (iterator.hasNext()) {
            var now = iterator.next();
            if (now.equals(dto.toRemoveValue)) {
                iterator.remove();
            }
        }

        return copyList;
    }

    public List<Long> v2(Dto dto) {
        List<Long> copyList = new ArrayList<>(dto.inputList);
        copyList.removeIf(i -> i.equals(dto.toRemoveValue));
        return copyList;
    }

    public List<Long> v3(Dto dto) {
        List<Long> copyList = new ArrayList<>(dto.inputList);

        return copyList.stream().
                filter(i -> !i.equals(dto.toRemoveValue)).toList();
    }

    public record Dto(List<Long> inputList, Long toRemoveValue) {}
}
