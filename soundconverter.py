import numpy as np
import librosa
import librosa.display
import matplotlib.pyplot as plt

audio_file = "E:/01. One Summer's Day.flac"

y, sr = librosa.load(audio_file)

# ปรับค่า n_fft และ hop_length เพื่อเพิ่มความละเอียด
n_fft = 16384
hop_length = 2048

D = librosa.amplitude_to_db(np.abs(librosa.stft(y, n_fft=n_fft, hop_length=hop_length)), ref=np.max)

plt.figure(figsize=(10, 6))
librosa.display.specshow(D, sr=sr, x_axis='time', y_axis='log', hop_length=hop_length)
plt.colorbar(format='%+2.0f dB')
plt.title('High-Resolution Spectrogram')
plt.show()
