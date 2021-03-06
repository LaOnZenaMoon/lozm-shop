package lozm.object.vo.image;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.File;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageVo {

    //client side
    private String imageName;
    private String imageSize;
    private String imageData;
    private String imageFileType;
    private int imageRotation;

    //server side
    private String imageFilePath;
    private File imageFile;

    public void setImageFile(File imageFile) {
        this.imageFile = imageFile;
    }

    public void setImageFilePath(String imageFilePath) {
        this.imageFilePath = imageFilePath;
    }

}
