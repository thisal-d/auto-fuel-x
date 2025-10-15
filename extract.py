import os

root_folder = "src"  # starting folder
output_path = "extract.txt"

with open(output_path, "w", encoding="utf-8") as text_file:
    # os.walk goes through all subfolders automatically
    for folder_path, subfolders, files in os.walk(root_folder):
        for file_name in files:
            if file_name.split(".")[-1] in ["java"]:
                file_path = os.path.join(folder_path, file_name)
                try:
                    with open(file_path, "r", encoding="utf-8") as f:
                        content = f.read()
                    text_file.write(f"File Path: {file_path}\n")
                    text_file.write(content.replace("\n", "").replace("  ", " ") + "\n\n")
                except Exception as e:
                    text_file.write(f"File Path: {file_path}\n")
                    text_file.write(f"[Error reading file: {e}]\n\n")

print(f"✅ Done! All files (including nested ones) have been written to {output_path}")
