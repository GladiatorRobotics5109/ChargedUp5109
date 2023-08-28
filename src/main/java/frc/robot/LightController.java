package frc.robot;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import frc.robot.util.ITest;

public class LightController implements ITest {
    private AddressableLED m_addressableLED;
    private AddressableLEDBuffer m_ledBuffer;

    public LightController(int lightStripChannel, int lightChannelLength) {
        m_addressableLED = new AddressableLED(lightStripChannel);
        m_ledBuffer = new AddressableLEDBuffer(lightChannelLength);
        m_addressableLED.setLength(m_ledBuffer.getLength());
    }

    public void setAllianceColor(DriverStation.Alliance alliance) {
        switch (alliance) {
            case Blue:
                for (int i = 0; i < m_ledBuffer.getLength(); i++) {
                    m_ledBuffer.setRGB(i, 0, 0, 255);
                }
                break;
            case Invalid:
                for (int i = 0; i < m_ledBuffer.getLength(); i++) {
                    m_ledBuffer.setRGB(i, 127, 0, 127);
                }
                break;
            case Red:
                for (int i = 0; i < m_ledBuffer.getLength(); i++) {
                    m_ledBuffer.setRGB(i, 255, 0, 0);
                }
                break;
        }

        m_addressableLED.setData(m_ledBuffer);
        m_addressableLED.start();
    }

    public void setBalanceColor() {
        for (int i = 0; i < m_ledBuffer.getLength(); i++) {
            m_ledBuffer.setRGB(i, 0, 255, 0);
        }
        m_addressableLED.setData(m_ledBuffer);
        m_addressableLED.start();
    }
    
    // sets the lightstrips to be unicorn vomit
    public void setUnicornVomit(int offset, boolean fieldRelative) {
        int hueVal = 0;

        for (int i = 0; i < m_ledBuffer.getLength(); i++) {
            m_ledBuffer.setHSV(i, (int) (((double) hueVal / (double) m_ledBuffer.getLength() + 40) * 180), 255, 255);
            hueVal += 1;
            if (i % 26 == 0) {
                hueVal += 10;
            }
        }

        if (fieldRelative) {    
            m_ledBuffer = fieldRelativize(m_ledBuffer, offset);
        }
        
        m_addressableLED.setData(m_ledBuffer);
        m_addressableLED.start();
    }

    // sets the lightstrips to be kevin's bugged out janked out bug
    public void setKevinsBuggedOutBug(int offset, boolean fieldRelative) {

        for (int i = 0; i < m_ledBuffer.getLength(); i++) {
            if (i >= m_ledBuffer.getLength() / 2) {
                m_ledBuffer.setRGB(i, 255, 0, 0);
            } else {
                m_ledBuffer.setRGB(i, 0, 0, 255);
            }
        }

        if (fieldRelative)
        {    
            m_ledBuffer = fieldRelativize(m_ledBuffer, offset);
        }

        m_addressableLED.setData(m_ledBuffer);
        m_addressableLED.start();
    }

    @Override
    public void test(boolean buttonPress)
    {
        if (buttonPress)
        {
            setAllianceColor(Alliance.Blue);;
        } else {
            setAllianceColor(Alliance.Red);
        }
    }
    
    // make a LED buffer field relative!!
    private AddressableLEDBuffer fieldRelativize(AddressableLEDBuffer buffer, int heading)
    {
        int bufferLength = buffer.getLength();
        AddressableLEDBuffer fieldRelativeBuffer = new AddressableLEDBuffer(bufferLength);
 //       int overflowStartIndex = 0;

        for (int i = 0; i < bufferLength; i++) // set LED data for non-overflowing LEDs
        {
<<<<<<< HEAD
            fieldRelativeBuffer.setLED((i + heading) % bufferLength, buffer.getLED(i));
=======
            if (i + heading > bufferLength)
            {
                overflowStartIndex = i;
                break;
            }
            //fieldRelativeBuffer.setLED(i + heading, buffer.getLED(i));
>>>>>>> 57e721323ab50ecb7a4c7711dbe8c443c7e70148
        }

        // for (int i = 0; i < overflowStartIndex; i++) // set LED data for the overflowing ones
        // {
        //     fieldRelativeBuffer.setLED(i, buffer.getLED(overflowStartIndex + i));
        // }

        return fieldRelativeBuffer;
    } 
}